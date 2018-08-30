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

    /**
     * <p>
     * 请求成功
     * </p>
     *
     * @param data 数据内容
     * @param <T>  对象泛型
     * @return
     */
    protected <T> RestResult<T> success(T data) {
        return RestResult.ok(data);
    }

    /**
     * <p>
     * 请求失败
     * </p>
     *
     * @param msg 提示内容
     * @return
     */
    protected RestResult<Object> failed(String msg) {
        return RestResult.failed(msg);
    }

    /**
     * <p>
     * 请求失败
     * </p>
     *
     * @param errorCode 请求错误码
     * @return
     */
    protected RestResult<Object> failed(RestErrorCode errorCode) {
        return RestResult.failed(errorCode);
    }	
	
}
