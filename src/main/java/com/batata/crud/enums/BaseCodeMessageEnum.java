package com.batata.crud.enums;

import com.batata.crud.base.code.BaseCodeMessage;

/**
 * <p>
 *  基础服务端请求返回信息枚举类
 * </p>
 *
 * @author czj
 * @since 2022-02-19
 **/
public enum BaseCodeMessageEnum implements BaseCodeMessage {

    OBJECT_NULL_POINT("500", "The object is null.");

    private String code;

    private String message;

    BaseCodeMessageEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
