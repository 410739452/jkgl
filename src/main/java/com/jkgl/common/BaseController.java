package com.jkgl.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>
 * 通用控制器
 * </p>
 *
 * @author PF
 *
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected <T> RestResult<T> success() {
    	return RestResult.restResult(null, RestResultCode.SUCCESS);
    }
    
    protected <T> RestResult<T> success(T data) {
        return RestResult.success(data);
    }
    
	protected <T> RestResult<T> fail() {
		return RestResult.fail(RestResultCode.FAILED);
	}

    protected <T> RestResult<T> fail(String msg) {
        return RestResult.fail(msg);
    }

    protected <T> RestResult<T> fail(RestResultCode errorCode) {
        return RestResult.fail(errorCode);
    }	

}
