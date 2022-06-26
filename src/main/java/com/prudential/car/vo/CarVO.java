package com.prudential.car.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @Author:qutingting
 * @Description: 车辆vo
 */
@Data
@EqualsAndHashCode
public class CarVO {
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "车型")
    private String model;
    @ApiModelProperty(value = "图片url")
    private String picture;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "价格")
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal price;
}
