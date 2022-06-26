package com.prudential.car.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.prudential.car.common.enums.OrderStateEnum;
import com.prudential.car.common.util.IdWorkerUtil;
import com.prudential.car.dto.OrderCustomerUpdateDTO;
import com.prudential.car.dto.OrderDTO;
import com.prudential.car.entity.Order;
import com.prudential.car.exception.ServiceException;
import com.prudential.car.mapper.OrderMapper;
import com.prudential.car.service.CarService;
import com.prudential.car.service.OrderService;
import com.prudential.car.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @Author:qutingting
 * @Description: 订单service实现类
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
	@Resource
	OrderMapper orderMapper;
	@Resource
	CarService carService;

	@Override
	public PageInfo<OrderVO> listOrder(Long customerId, int pageNum, int pageSize) {
		PageInfo<OrderVO> page = PageHelper.startPage(pageNum, pageSize)
				.doSelectPageInfo(() -> orderMapper.listOrder(customerId)
						.stream().map(order -> {
							OrderVO orderVo = new OrderVO();
							if (order != null) {
								BeanUtils.copyProperties(order, orderVo);
							}
							return orderVo;
						}).collect(Collectors.toList()));
		return page;
	}

	@Override
	public OrderVO getOrderById(Long orderId) {
		Order order = orderMapper.getOrderById(orderId);
		OrderVO orderVo = new OrderVO();
		if (order != null) {
			BeanUtils.copyProperties(order, orderVo);
		}
		return orderVo;
	}

	@Override
	public Long getOrderId() {
		return IdWorkerUtil.generateId();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrder(OrderDTO orderDto) {
		//校验租车开始时间与结束时间
		checkRentalTime(orderDto.getRentalStartTime(), orderDto.getRentalEndTime());

		//新增订单
		Order order = new Order();
		BeanUtils.copyProperties(orderDto, order);
		//假装有金额计算了哈
		order.setState(OrderStateEnum.CREATED.getCode());
		order.setCreatedTime(new Date());
		order.setModifiedTime(new Date());
		try {
			orderMapper.insertOrder(order);
		} catch (Exception e) {
			//订单编号重复直接return
			if (e instanceof DuplicateKeyException || e instanceof MySQLIntegrityConstraintViolationException) {
				log.warn("订单编号重复： " + order.getId());
				return;
			} else {
				throw e;
			}
		}

		//扣车辆库存
		Long carModelId = orderDto.getCarModelId();
		int i = carService.reduceStock(carModelId);

		if (i <= 0) {
			throw new ServiceException("订单创建失败！");
		}
	}

	@Override
	public void updateOrder(OrderCustomerUpdateDTO orderDto) {
		Long orderId = orderDto.getId();
		Order order = orderMapper.getOrderById(orderId);
		if (order == null) {
			throw new ServiceException("订单不存在！");
		}

		//订单状态判断
		int state = order.getState();
		if (OrderStateEnum.PROCESSING.getCode() > state) {
			//校验租车开始时间与结束时间
			checkRentalTime(orderDto.getRentalStartTime(), orderDto.getRentalEndTime());
			//更新订单
			Order order2Update = new Order();
			BeanUtils.copyProperties(orderDto,order2Update);
			//假装有金额计算了哈
			order2Update.setModifiedTime(new Date());
			orderMapper.updateOrder(order2Update);
		} else {
			throw new ServiceException("当前订单状态不允许更新！");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void cancelOrder(Long orderId) {
		//订单状态判断
		Order order = orderMapper.getOrderById(orderId);
		if (order == null) {
			throw new ServiceException("订单不存在！");
		}
		if (OrderStateEnum.CREATED.getCode() != order.getState()) {
			throw new ServiceException("当前订单状态不允许取消！");
		}
		//订单取消
		orderMapper.cancelOrder(orderId);
		//加库存
		carService.addStock(order.getCarModelId());
	}

	/**
	 * 校验租车开始时间与结束时间
	 * * 都不可为空，开始时间要大于当前时间，开始时间要小于结束时间
	 *
	 * @param rentalStartTime
	 * @param rentalEndTime
	 */
	private void checkRentalTime(Date rentalStartTime, Date rentalEndTime) {
		if (rentalStartTime == null) {
			throw new ServiceException("租车开始时间不可为空！");
		}
		if (rentalEndTime == null) {
			throw new ServiceException("租车结束时间不可为空！");
		}
		if (rentalStartTime.compareTo(new Date()) <= 0) {
			throw new ServiceException("租车开始时间不可早于当前时间！");
		}
		if (rentalStartTime.compareTo(rentalEndTime) >= 0) {
			throw new ServiceException("租车结束时间不可早于开始时间！");
		}
	}
}
