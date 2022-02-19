package com.batata.crud.toolkit;

import com.batata.crud.base.code.BaseException;
import com.batata.crud.base.code.DefaultCodeMessage;
import com.batata.crud.constant.HttpCodeConstant;

/**
 * <p>
 * 断言工具
 * </p>
 *
 * @author czj
 * @since 2022-02-19
 **/
public class Assert {

    /**
     * not null 校验，若待校验对象为 null, 则抛出异常
     *
     * @param o 待校验对象
     * @param message 异常抛出信息
     */
    public static void isNotNull(Object o, String message) {
        if (o == null) {
            throw new BaseException(new DefaultCodeMessage(HttpCodeConstant.FAILED, message));
        }
    }

    /**
     * null 校验，若待校验对象不为 null，则抛出异常
     *
     * @param o 待校验对象
     * @param message 异常抛出信息
     */
    public static void isNull(Object o, String message) {
        if (o != null) {
            throw new BaseException(new DefaultCodeMessage(HttpCodeConstant.FAILED, message));
        }
    }
}
