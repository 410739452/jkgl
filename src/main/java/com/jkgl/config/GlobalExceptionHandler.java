package com.jkgl.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jkgl.common.RestErrorCode;
import com.jkgl.common.RestException;
import com.jkgl.common.RestResult;

/**
 * 
 * <p>
 * 全局异常处理
 * </p>
 *
 * @author PF
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * <p>
	 * 自定义 REST 业务异常
	 * <p>
	 *
	 * @param e 异常类型
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public RestResult<Object> handleBadRequest(Exception e) {
		/*
		 * 业务逻辑异常
		 */
		if (e instanceof RestException) {
			RestErrorCode errorCode = ((RestException) e).getErrorCode();
			if (null != errorCode) {
				logger.debug("Rest request error, {}", errorCode.toString());
				return RestResult.failed(errorCode);
			}
			logger.debug("Rest request error, {}", e.getMessage());
			return RestResult.failed(e.getMessage());
		}

		/*
		 * 参数校验异常
		 */
		if (e instanceof BindException) {
			BindingResult bindingResult = ((BindException) e).getBindingResult();
			if (null != bindingResult && bindingResult.hasErrors()) {
				List<Object> jsonList = new ArrayList<>();
				bindingResult.getFieldErrors().stream().forEach(fieldError -> {
					Map<String, Object> jsonObject = new HashMap<>(2);
					jsonObject.put("name", fieldError.getField());
					jsonObject.put("msg", fieldError.getDefaultMessage());
					jsonList.add(jsonObject);
				});
				return RestResult.restResult(jsonList, RestErrorCode.FAILED);
			}
		}

		/**
		 * 系统内部异常，打印异常栈
		 */
		logger.error("Error: handleBadRequest StackTrace : {}", e);
		return RestResult.failed(RestErrorCode.FAILED);
	}
}
