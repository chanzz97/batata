package com.batata.crud.dto;

import com.batata.crud.annotation.valid.ValidInsert;
import com.batata.crud.annotation.valid.ValidUpdate;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 请求体 DTO
 * </p>
 *
 * @author czj
 * @since 2022-02-19
 **/
public class RequestBodyDTO<T> {

    /**
     * 接受的数据
     */
    @NotNull.List({
            @NotNull(message = "参数对象 data 不能为空"),
            @NotNull(message = "参数对象 data 不能为空", groups = {ValidInsert.class, ValidUpdate.class})
    })
    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"data\":")
                .append(data);
        sb.append('}');
        return sb.toString();
    }
}
