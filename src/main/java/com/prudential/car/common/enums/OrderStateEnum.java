package com.prudential.car.common.enums;

import lombok.Getter;

/**
 * @Author:qutingting
 * @Description: 订单状态枚举类
 */
@Getter
public enum OrderStateEnum {
    CREATED(1,"新建"),
    TOBEUSED(2,"待使用"),
    PROCESSING(3,"进行中"),
    COMPLETED(4,"已完成"),
    CANCELED(5,"已取消");

    private int code;

    private String name;

    OrderStateEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
