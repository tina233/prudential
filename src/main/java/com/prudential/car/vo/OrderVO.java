package com.prudential.car.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author:qutingting
 * @Description: 订单Vo
 */
@Data
@EqualsAndHashCode
public class OrderVO {
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "租车开始时间")
    private Date rentalStartTime;
    @ApiModelProperty(value = "租车结束时间")
    private Date rentalEndTime;
    @ApiModelProperty(value = "实际还车时间")
    private Date actualReturnTime;
    @ApiModelProperty(value = "订单状态")
    private Integer state;
    @ApiModelProperty(value = "客户id")
    private Long customerId;
    @ApiModelProperty(value = "车型id")
    private Long carModelId;
    @ApiModelProperty(value = "车型")
    private String carModel;
    @ApiModelProperty(value = "总金额")
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal totalAmount;
    @ApiModelProperty(value = "已付金额")
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal paidAmount;
    @ApiModelProperty(value = "待付金额")
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal residualAmount;
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;
}
