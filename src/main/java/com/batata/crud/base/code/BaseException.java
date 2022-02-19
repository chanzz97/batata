package com.batata.crud.base.code;

/**
 * <p>
 * 基础异常类
 * </p>
 *
 * @author czj
 * @since 2022-02-19
 **/
public class BaseException extends RuntimeException {

    private final BaseCodeMessage codeMessage;

    public BaseException(BaseCodeMessage codeMessage) {
        super("[" + codeMessage.getCode() + "]" + codeMessage.getMessage());
        this.codeMessage = codeMessage;
    }

    public BaseException(Throwable cause, BaseCodeMessage codeMessage) {
        super("[" + codeMessage.getCode() + "]" + codeMessage.getMessage(), cause);
        this.codeMessage = codeMessage;
    }

    public BaseCodeMessage getCodeMessage() {
        return codeMessage;
    }
}
