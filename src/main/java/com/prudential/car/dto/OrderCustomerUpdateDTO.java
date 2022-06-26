package com.prudential.car.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author:qutingting
 * @Description: 用户更新订单dto
 */
@Data
@EqualsAndHashCode
public class OrderCustomerUpdateDTO {

    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "租车开始时间")
    private Date rentalStartTime;
    @ApiModelProperty(value = "租车结束时间")
    private Date rentalEndTime;
    @ApiModelProperty(value = "总金额")
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal totalAmount;
    @ApiModelProperty(value = "已付金额")
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal paidAmount;
    @ApiModelProperty(value = "待付金额")
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal residualAmount;

}
