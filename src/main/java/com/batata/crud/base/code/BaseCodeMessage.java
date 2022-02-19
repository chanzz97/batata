package com.batata.crud.base.code;

import java.io.Serializable;

/**
 * <p>
 * 基础的请求返回码接口
 * </p>
 *
 * @author czj
 * @since 2022-02-19
 **/
public interface BaseCodeMessage extends Serializable {

    /**
     * 编码
     *
     * @return 操作返回结果编码
     */
    String getCode();

    /**
     * 信息
     *
     * @return 操作执行后的具体信息
     */
    String getMessage();

}
