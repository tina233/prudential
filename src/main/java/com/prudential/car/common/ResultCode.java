package com.prudential.car.common;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author:qutingting
 * @Description: 错误码
 */
public enum ResultCode {
	SUCCESS(HttpServletResponse.SC_OK, "Operation is Successful"),
	//业务逻辑异常
	FAILURE(HttpServletResponse.SC_BAD_REQUEST, "Biz Exception"),
	//参数合法性异常
	PARAM_VALID_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Validation Error"),
	//登录权限异常
	UN_AUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "Request Unauthorized"),
	//404
	NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "404 Not Found"),
	//Method Not Supported
	METHOD_NOT_SUPPORTED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method Not Supported"),
	//系统错误
	INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error"),
	//拒绝访问
	REQ_REJECT(HttpServletResponse.SC_FORBIDDEN, "用户没权限");

	final int code;
	final String msg;

	ResultCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
