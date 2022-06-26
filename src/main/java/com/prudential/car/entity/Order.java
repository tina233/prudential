package com.prudential.car.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author:qutingting
 * @Description: 订单实体类
 */
@Data
@EqualsAndHashCode
public class Order {
    private Long id;

    private Boolean deleted;

    private Date rentalStartTime;

    private Date rentalEndTime;

    private Date actualReturnTime;

    private Integer state;

    private Long customerId;

    private Long carModelId;

    private String carModel;

    private BigDecimal totalAmount;

    private BigDecimal paidAmount;

    private BigDecimal residualAmount;

    private Date createdTime;

    private Date modifiedTime;

}
