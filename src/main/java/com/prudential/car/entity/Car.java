package com.prudential.car.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author:qutingting
 * @Description: 车辆实体类
 */
@Data
@EqualsAndHashCode
public class Car {
    private Long id;

    private Boolean deleted;

    private String model;

    private Integer stock;

    private String picture;

    private String description;

    private BigDecimal price;

    private Date createdTime;

    private Date modifiedTime;

}
