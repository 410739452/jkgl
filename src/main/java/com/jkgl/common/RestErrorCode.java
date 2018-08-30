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
public enum RestErrorCode {
	/**
	 * 失败
	 */
	FAILED("0", "失败"),
	
	/**
	 * 成功
	 */
	SUCCESS("1", "成功");

	private final String code;
	private final String msg;

	RestErrorCode(final String code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

	public static RestErrorCode fromCode(String code) {
		RestErrorCode[] ecs = RestErrorCode.values();
		for (RestErrorCode ec : ecs) {
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
