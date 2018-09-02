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

	public RestResult(RestResultCode code) {
		code = Optional.ofNullable(code).orElse(RestResultCode.FAILED);
		this.code = code.getCode();
		this.msg = code.getMsg();
	}

	public static <T> RestResult<T> restResult(T data, RestResultCode code) {
		return restResult(data, code.getCode(), code.getMsg());
	}

	private static <T> RestResult<T> restResult(T data, String code, String msg) {
		RestResult<T> restResult = new RestResult<>();
		restResult.setCode(code);
		restResult.setData(data);
		restResult.setMsg(msg);
		return restResult;
	}

	public static <T> RestResult<T> success(T data) {
		return restResult(data, RestResultCode.SUCCESS);
	}

	public static <T> RestResult<T> fail(String msg) {
		return restResult(null, RestResultCode.FAILED.getCode(), msg);
	}

	public static <T> RestResult<T> fail(RestResultCode errorCode) {
		return restResult(null, errorCode);
	}

}
