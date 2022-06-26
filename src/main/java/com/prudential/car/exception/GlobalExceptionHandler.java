package com.prudential.car.exception;

import com.prudential.car.common.RestResult;
import com.prudential.car.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @Author:qutingting
 * @Description: 全局异常处理类, 捕获所有controller抛出的异常, 统一json格式返回
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public RestResult handleError(MissingServletRequestParameterException e) {
		log.warn("Missing Request Parameter", e);
		String message = String.format("Missing Request Parameter: %s", e.getParameterName());
		return new RestResult(ResultCode.PARAM_VALID_ERROR.getCode(), message);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public RestResult handleError(MethodArgumentTypeMismatchException e) {
		log.warn("Method Argument Type Mismatch", e);
		String message = String.format("Method Argument Type Mismatch: %s", e.getName());
		return new RestResult(ResultCode.PARAM_VALID_ERROR.getCode(), message);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public RestResult handleError(MethodArgumentNotValidException e) {
		log.warn("Method Argument Not Valid", e);
		BindingResult result = e.getBindingResult();
		FieldError error = result.getFieldError();
		String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
		return new RestResult(ResultCode.PARAM_VALID_ERROR.getCode(), message);
	}

	@ExceptionHandler(BindException.class)
	public RestResult handleError(BindException e) {
		log.warn("Bind Exception", e);
		FieldError error = e.getFieldError();
		String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
		return new RestResult(ResultCode.PARAM_VALID_ERROR.getCode(), message);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public RestResult handleError(ConstraintViolationException e) {
		log.warn("Constraint Violation", e);
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		ConstraintViolation<?> violation = violations.iterator().next();
		String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
		String message = String.format("%s:%s", path, violation.getMessage());
		return new RestResult(ResultCode.PARAM_VALID_ERROR.getCode(), message);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public RestResult handleError(NoHandlerFoundException e) {
		log.warn("404 Not Found", e);
		return new RestResult(ResultCode.NOT_FOUND.getCode(), e.getMessage());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public RestResult handleError(HttpMessageNotReadableException e) {
		log.error("Message Not Readable", e);
		return new RestResult(ResultCode.FAILURE.getCode(), e.getMessage());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public RestResult handleError(HttpRequestMethodNotSupportedException e) {
		log.error("Request Method Not Supported", e);
		return new RestResult(ResultCode.FAILURE.getCode(), e.getMessage());
	}

	@ExceptionHandler(ServiceException.class)
	public RestResult handleError(ServiceException e) {
		log.error("ServiceException Exception", e);
		return new RestResult(e.getResultCode().getCode(), e.getMessage());
	}

	@ExceptionHandler(Throwable.class)
	public RestResult handleError(Throwable e) {
		log.error("Internal Server Error", e);
		return new RestResult(ResultCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
	}
}
