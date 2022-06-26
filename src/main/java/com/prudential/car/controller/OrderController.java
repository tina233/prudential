package com.prudential.car.controller;

import com.prudential.car.common.PageRestResult;
import com.prudential.car.common.RestResult;
import com.prudential.car.common.util.SecurityUtil;
import com.prudential.car.dto.OrderCustomerUpdateDTO;
import com.prudential.car.dto.OrderDTO;
import com.prudential.car.service.OrderService;
import com.prudential.car.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:qutingting
 * @Description: 订单controller
 */
@RestController
@RequestMapping("/order")
@Api(value = "OrderController")
public class OrderController {
	@Resource
	private OrderService orderService;

	@ApiOperation(value = "查询订单列表")
	@GetMapping(value = "/list")
	public RestResult<List<OrderVO>> getOrderList(@RequestParam int pageNum, @RequestParam int pageSize) {
		return PageRestResult.transferFromPageInfo(orderService.listOrder(SecurityUtil.getCustomId(), pageNum, pageSize));
	}

	@ApiOperation(value = "根据订单id查询订单")
	@GetMapping(value = "/get/{orderId}")
	public RestResult<OrderVO> getOrderById(@PathVariable Long orderId) {
		return RestResult.success(orderService.getOrderById(orderId));
	}

	@ApiOperation(value = "生成唯一订单号")
	@GetMapping(value = "/get/order-id")
	public RestResult<Long> getOrderId() {
		return RestResult.success(orderService.getOrderId());
	}

	@ApiOperation(value = "新增订单")
	@PostMapping(value = "/create")
	public RestResult saveOrder(@RequestBody OrderDTO orderDto) {
		orderService.saveOrder(orderDto);
		return RestResult.success();
	}

	@ApiOperation(value = "更新订单")
	@PatchMapping(value = "/update")
	public RestResult updateOrder(@RequestBody OrderCustomerUpdateDTO orderDto) {
		orderService.updateOrder(orderDto);
		return RestResult.success();
	}

	@ApiOperation(value = "取消订单")
	@PatchMapping(value = "/cancel")
	public RestResult cancelOrder(@RequestBody Long orderId) {
		orderService.cancelOrder(orderId);
		return RestResult.success();
	}

}
