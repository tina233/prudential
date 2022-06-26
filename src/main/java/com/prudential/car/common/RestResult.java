package com.prudential.car.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author:qutingting
 * @Description: 通用接口返回结果封装
 */
@Getter
@NoArgsConstructor
public class RestResult<T> {
	private String message;

	private int code = ResultCode.SUCCESS.getCode();

	private T data;

	public boolean isSuccess() {
		return code == ResultCode.SUCCESS.getCode();
	}

	public static RestResult success() {
		return new RestResult();
	}

	public static <T> RestResult<T> success(T data) {
		return new RestResult(data);
	}

	public RestResult(T data) {
		this.data = data;
	}

	public RestResult(int code, String message) {
		this.message = message;
		this.code = code;
	}
}
