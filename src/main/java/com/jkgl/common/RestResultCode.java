package com.jkgl.common;

/**
 * 
 * <p>
 * REST API 错误码
 * </p>
 *
 * @author PF
 *
 */
public enum RestResultCode {
	SUCCESS("0", "成功"),
	FAILED("1", "失败"),
	
	UNAUTHENTICATED("10000", "未认证"),
	UNAUTHORIZED("10001", "无权限"),
	TOKEN_INVALID("10002", "Token认证失败"),
	USER_NOT_EXISTED("10003", "用户不存在"),
	USERNAME_OR_PASSWORD_ERROR("10004", "用户名或密码错误"),
	;
	

	private final String code;
	private final String msg;

	RestResultCode(final String code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

	public static RestResultCode fromCode(String code) {
		RestResultCode[] ecs = RestResultCode.values();
		for (RestResultCode ec : ecs) {
			if (ec.getCode().equalsIgnoreCase(code)) {
				return ec;
			}
		}
		return SUCCESS;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	@Override
	public String toString() {
		return String.format(" RestErrorCode:{code=%s, msg=%s} ", code, msg);
	}
}
