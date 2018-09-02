package com.jkgl.common;

import java.util.Collection;
import java.util.Map;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

/**
 * 
 * <p>
 * REST API 业务断言
 * </p>
 *
 * @author PF
 *
 */
public class RestAssert {
	protected RestAssert() {
        // to do noting
    }

	/**
	 * 大于O
	 */
	public static void gtZero(Integer num, RestResultCode errorCode) {
		if (num == null || num <= 0) {
			RestAssert.fail(errorCode);
		}
	}

	/**
	 * 大于等于O
	 */
	public static void geZero(Integer num, RestResultCode errorCode) {
		if (num == null || num < 0) {
			RestAssert.fail(errorCode);
		}
	}

	/**
	 * num1大于num2
	 */
	public static void gt(Integer num1, Integer num2, RestResultCode errorCode) {
		if (num1 <= num2) {
			RestAssert.fail(errorCode);
		}
	}

	/**
	 * num1大于等于num2
	 */
	public static void ge(Integer num1, Integer num2, RestResultCode errorCode) {
		if (num1 < num2) {
			RestAssert.fail(errorCode);
		}
	}

	/**
	 * obj1 eq obj2
	 */
	public static void eq(Object obj1, Object obj2, RestResultCode errorCode) {
		if (!obj1.equals(obj2)) {
			RestAssert.fail(errorCode);
		}
	}

	public static void isTrue(boolean condition, RestResultCode errorCode) {
		if (!condition) {
			RestAssert.fail(errorCode);
		}
	}

	public static void isFalse(boolean condition, RestResultCode errorCode) {
		if (condition) {
			RestAssert.fail(errorCode);
		}
	}

	public static void isNull(RestResultCode errorCode, Object... conditions) {
		if (ObjectUtils.isNotNull(conditions)) {
			RestAssert.fail(errorCode);
		}
	}

	public static void notNull(RestResultCode errorCode, Object... conditions) {
		if (ObjectUtils.isNull(conditions)) {
			RestAssert.fail(errorCode);
		}
	}

	/**
	 * <p>
	 * 失败结果
	 * </p>
	 *
	 * @param errorCode 异常错误码
	 */
	public static void fail(RestResultCode errorCode) {
		throw new RestException(errorCode);
	}

	public static void fail(boolean condition, RestResultCode errorCode) {
		if (condition) {
			RestAssert.fail(errorCode);
		}
	}

	public static void fail(String message) {
		throw new RestException(message);
	}

	public static void fail(boolean condition, String message) {
		if (condition) {
			RestAssert.fail(message);
		}
	}

	public static void notEmpty(Object[] array, RestResultCode errorCode) {
		if (ObjectUtils.isEmpty(array)) {
			RestAssert.fail(errorCode);
		}
	}

	public static void noNullElements(Object[] array, RestResultCode errorCode) {
		if (array != null) {
			for (Object element : array) {
				if (element == null) {
					RestAssert.fail(errorCode);
				}
			}
		}
	}

	public static void notEmpty(Collection<?> collection, RestResultCode errorCode) {
		if (CollectionUtils.isNotEmpty(collection)) {
			RestAssert.fail(errorCode);
		}
	}

	public static void notEmpty(Map<?, ?> map, RestResultCode errorCode) {
		if (ObjectUtils.isEmpty(map)) {
			RestAssert.fail(errorCode);
		}
	}

	public static void isInstanceOf(Class<?> type, Object obj, RestResultCode errorCode) {
		RestAssert.notNull(errorCode, type);
		if (!type.isInstance(obj)) {
			RestAssert.fail(errorCode);
		}
	}

	public static void isAssignable(Class<?> superType, Class<?> subType, RestResultCode errorCode) {
		RestAssert.notNull(errorCode, superType);
		if (subType == null || !superType.isAssignableFrom(subType)) {
			RestAssert.fail(errorCode);
		}
	}
}
