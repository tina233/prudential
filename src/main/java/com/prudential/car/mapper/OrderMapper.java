package com.prudential.car.mapper;

import com.prudential.car.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:qutingting
 * @Description: OrderMapper
 */
@Mapper
public interface OrderMapper {
    List<Order> listOrder(Long customerId);

    Order getOrderById(Long orderId);

    void insertOrder(Order order);

    void updateOrder(Order order);

    void cancelOrder(Long orderId);
}
