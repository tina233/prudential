package com.prudential.car.service;

import com.github.pagehelper.PageInfo;
import com.prudential.car.dto.OrderCustomerUpdateDTO;
import com.prudential.car.dto.OrderDTO;
import com.prudential.car.vo.OrderVO;

/**
 * @Author:qutingting
 * @Description: 订单service接口
 */
public interface OrderService {

    /**
     * 查询订单列表
     * @param customerId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<OrderVO> listOrder(Long customerId, int pageNum, int pageSize);

    /**
     * 生成唯一订单号
     * @return
     */
    Long getOrderId();

    /**
     * 根据订单id查询订单
     * @param orderId
     * @return
     */
    OrderVO getOrderById(Long orderId);

    /**
     * 新增订单
     * @param orderDto
     */
    void saveOrder(OrderDTO orderDto);

    /**
     * 更新订单
     * @param orderDto
     */
    void updateOrder(OrderCustomerUpdateDTO orderDto);

    /**
     * 取消订单
     * @param orderId
     */
    void cancelOrder(Long orderId);
}
