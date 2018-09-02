package com.jkgl.common;

/**
 * 
 * <p>
 * REST API 请求异常类
 * </p>
 *
 * @author PF
 *
 */
public class RestException extends RuntimeException {

	private static final long serialVersionUID = -4328619477137494876L;

	/**
	 * 错误码
	 */
	private RestResultCode errorCode;

	public RestException(RestResultCode errorCode) {
		super(errorCode.getMsg());
		this.errorCode = errorCode;
	}

	public RestException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestException(String message) {
		super(message);
	}

	public RestException(Throwable cause) {
		super(cause);
	}

	public RestResultCode getErrorCode() {
		return errorCode;
	}

}
