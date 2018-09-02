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
