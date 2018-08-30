package com.jkgl.common;

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
	 * 处理REST异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = RestException.class)
	public RestResult<Object> handleRestException(RestException e) {
		RestErrorCode errorCode = e.getErrorCode();
		if (null != errorCode) {
			logger.debug("Rest request error, {}", errorCode.toString());
			return RestResult.failed(errorCode);
		}
		logger.debug("Rest request error, {}", e.getMessage());
		return RestResult.failed(e.getMessage());
	}

	/**
	 * 处理参数校验异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = BindException.class)
	public RestResult<Object> handleBindException(BindException e) {
		BindingResult bindingResult = e.getBindingResult();
		List<Object> jsonList = new ArrayList<>();
		if (null != bindingResult && bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				Map<String, Object> jsonObject = new HashMap<>(2);
				jsonObject.put("name", fieldError.getField());
				jsonObject.put("msg", fieldError.getDefaultMessage());
				jsonList.add(jsonObject);
			});
		}
		return RestResult.restResult(jsonList, RestErrorCode.FAILED);
	}

	/**
	 * 默认异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public RestResult<Object> handleBadRequest(Exception e) {
		logger.error("Error: handleBadRequest StackTrace : {}", e);
		return RestResult.failed(RestErrorCode.FAILED);
	}
}
