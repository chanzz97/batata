package com.batata.crud.base.code;

import com.batata.crud.enums.BaseCodeMessageEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

/**
 * <p>
 * 默认返回编码类
 * </p>
 *
 * @author czj
 * @since 2022-02-19
 **/
public class DefaultCodeMessage implements BaseCodeMessage {

    public static final DefaultCodeMessage SUCCESS = new DefaultCodeMessage("200", "success");

    private String code;

    private String message;

    public DefaultCodeMessage() {
    }

    public DefaultCodeMessage(@NonNull String code, @NonNull String message) {
        // 校验编码与信息是否有效，如为非法的数据则抛出空指针异常
        codeChecked(code);
        messageChecked(message);

        this.code = code;
        this.message = message;

    }

    public DefaultCodeMessage(@NonNull BaseCodeMessageEnum codeMessage) {
        this.code = codeMessage.getCode();
        this.message = codeMessage.getMessage();

    }

    public void setCode(@NonNull String code) {
        codeChecked(code);

        this.code = code;
    }

    public void setMessage(@NonNull String message) {
        messageChecked(message);

        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * 编码校验，若为非法编码则抛出 {@link NullPointerException}
     *
     * @param code 编码
     */
    private void codeChecked(@NonNull String code) {
        if (StringUtils.isBlank(code)) {
            throw new NullPointerException("The code is marked non-null, but is null");
        }
    }

    /**
     * 信息校验，若为非法信息则抛出 {@link NullPointerException}
     *
     * @param message 信息
     */
    private void messageChecked(@NonNull String message) {
        if (StringUtils.isBlank(message)) {
            throw new NullPointerException("The message is marked non-null, but is null");
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"code\":\"")
                .append(code).append('\"');
        sb.append(",\"message\":\"")
                .append(message).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
