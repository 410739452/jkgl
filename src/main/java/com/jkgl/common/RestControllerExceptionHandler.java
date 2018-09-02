package com.jkgl.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 
 * <p>
 * Controller异常处理
 * </p>
 *
 * @author PF
 *
 */
@RestControllerAdvice
public class RestControllerExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestControllerExceptionHandler.class);

    @ExceptionHandler(ShiroException.class)
    public RestResult<Object> handleShiroException(ShiroException e) {
		logger.error("Error: handleShiroException StackTrace : {}", e);
		return RestResult.fail(e.getMessage());
    }
    
    @ExceptionHandler(value = UnauthenticatedException.class)
    public RestResult<Object> handleUnauthenticatedException(UnauthenticatedException e) {
    	logger.error("Error: handleUnauthenticatedException StackTrace : {}", e);
    	return RestResult.fail("没有认证");
    }
    
	@ExceptionHandler(value = AuthenticationException.class)
	public RestResult<Object> handleAuthenticationException(AuthenticationException e) {
		logger.error("Error: handleAuthenticationException StackTrace : {}", e);
		return RestResult.fail(e.getMessage());
	}   
	
	@ExceptionHandler(value = RestException.class)
	public RestResult<Object> handleRestException(RestException e) {
		RestResultCode errorCode = e.getErrorCode();
		if (null != errorCode) {
			logger.debug("Rest request error, {}", errorCode.toString());
			return RestResult.fail(errorCode);
		}
		logger.debug("Rest request error, {}", e.getMessage());
		return RestResult.fail(e.getMessage());
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
		return RestResult.restResult(jsonList, RestResultCode.FAILED);
	}
	
	/**
	 * 默认异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public RestResult<Object> handleException(Exception e) {
		logger.error("Error: handleException StackTrace : {}", e);
		return RestResult.fail(e.getMessage());
	}
}
