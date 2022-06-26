package com.prudential.car.exception;

import com.prudential.car.common.ResultCode;

/**
 * @Author:qutingting
 * @Description: 全局统一运行时异常
 * @see GlobalExceptionHandler
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 2359767895161832954L;

	private final ResultCode resultCode;

	public ServiceException(String message) {
		super(message);
		this.resultCode = ResultCode.FAILURE;
	}

	public ServiceException(ResultCode resultCode) {
		super(resultCode.getMsg());
		this.resultCode = resultCode;
	}

	public ServiceException(ResultCode resultCode, String msg) {
		super(msg);
		this.resultCode = resultCode;
	}

	public ServiceException(ResultCode resultCode, Throwable cause) {
		super(resultCode.getMsg(), cause);
		this.resultCode = resultCode;
	}

	public ServiceException(String msg, Throwable cause) {
		super(msg, cause);
		this.resultCode = ResultCode.FAILURE;
	}

	public ResultCode getResultCode() {
		return resultCode;
	}
}
