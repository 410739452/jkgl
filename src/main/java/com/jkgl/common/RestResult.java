package com.jkgl.common;

import java.util.Optional;

import lombok.Data;

/**
 * 
 * <p>
 * REST API 返回结果类
 * </p>
 *
 * @author PF
 *
 * @param <T> 返回payload的数据类型
 */
@Data
public class RestResult<T> {

	/**
	 * 业务错误码
	 */
	private String code;

	/**
	 * 结果集
	 */
	private T data;

	/**
	 * 描述
	 */
	private String msg;

	public RestResult() {
	}

	public RestResult(RestErrorCode errorCode) {
		errorCode = Optional.ofNullable(errorCode).orElse(RestErrorCode.FAILED);
		this.code = errorCode.getCode();
		this.msg = errorCode.getMsg();
	}

	public static <T> RestResult<T> restResult(T data, RestErrorCode errorCode) {
		return restResult(data, errorCode.getCode(), errorCode.getMsg());
	}

	private static <T> RestResult<T> restResult(T data, String code, String msg) {
		RestResult<T> restResult = new RestResult<>();
		restResult.setCode(code);
		restResult.setData(data);
		restResult.setMsg(msg);
		return restResult;
	}

	public static <T> RestResult<T> ok(T data) {
		return restResult(data, RestErrorCode.SUCCESS);
	}

	public static <T> RestResult<T> failed(String msg) {
		return restResult(null, RestErrorCode.FAILED.getCode(), msg);
	}

	public static <T> RestResult<T> failed(RestErrorCode errorCode) {
		return restResult(null, errorCode);
	}

	public boolean ok() {
		return RestErrorCode.SUCCESS.getCode().equals(this.code);
	}

	/**
	 * 服务间调用非业务正常，异常直接释放
	 */
	public T serviceData() {
		if (!ok()) {
			throw new RestException(this.msg);
		}
		return data;
	}

}
