package com.batata.crud.enums;

/**
 * <p>
 * 查询条件枚举，只提供常用的几种查询条件
 * </p>
 *
 * @author czj
 * @since 2022-02-20
 **/
public enum QueryEnum {

    /**
     * 等于
     */
    EQ,

    /**
     * 大于
     */
    GT,

    /**
     * 大于等于
     */
    GE,

    /**
     * 小于
     */
    LT,

    /**
     * 小于等于
     */
    LE,

    /**
     * 区间
     *
     * 支持 {@link java.util.List}、array、String
     * 若值为字符串时，需使用 <em>","</em> 分隔，两个值间不能存在空格
     */
    BETWEEN,

    /**
     * 模糊
     */
    LIKE,

    /**
     * 区间
     *
     * 支持 {@link java.util.List}、array、String
     * 若值为字符串时，需使用 <em>","</em> 分隔，两个值间不能存在空格
     */
    IN

}
