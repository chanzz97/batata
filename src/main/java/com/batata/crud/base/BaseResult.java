package com.batata.crud.base;

import com.batata.crud.base.code.BaseCodeMessage;
import com.batata.crud.base.code.DefaultCodeMessage;
import lombok.NonNull;

/**
 * <p>
 * 基础结果类
 * 用户可通过继承此类，实现自己的返回类
 * </p>
 *
 * @author czj
 * @since 2022-02-19
 **/
public class BaseResult<T> {

    /**
     * 操作是否成功
     */
    protected Boolean success;

    /**
     * 返回信息
     */
    protected BaseCodeMessage message;

    /**
     * 返回的记过
     */
    protected T data;

    public BaseResult() {
    }

    public BaseResult(Boolean success, @NonNull BaseCodeMessage message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static BaseResult<String> success() {
        return new BaseResult<>(true, DefaultCodeMessage.SUCCESS, null);
    }

    public static <T> BaseResult<T> success(T data) {
        return new BaseResult<T>(true, DefaultCodeMessage.SUCCESS, data);
    }

    public static BaseResult<String> error(BaseCodeMessage codeMessage) {
        return new BaseResult<>(false, codeMessage, null);
    }

    public static <T> BaseResult<T> error(BaseCodeMessage codeMessage, T data) {
        return new BaseResult<T>(false, codeMessage, data);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @NonNull
    public BaseCodeMessage getMessage() {
        return message;
    }

    public void setMessage(@NonNull BaseCodeMessage message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"success\":")
                .append(success);
        sb.append(",\"message\":")
                .append(message);
        sb.append(",\"data\":")
                .append(data);
        sb.append('}');
        return sb.toString();
    }
}
