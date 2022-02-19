package com.batata.crud.annotation;

import com.batata.crud.enums.QueryEnum;

import java.lang.annotation.*;

/**
 * <p>
 * 查询条件注解
 * </p>
 *
 * @author czj
 * @since 2022-02-20
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface Query {

    /**
     * 查询字段，对应数据库表中的字段名
     * <p>
     * 默认为""，此时会用参数名代替(若字段为驼峰格式，则会将其格式化)
     *
     * @return real column name
     */
    String column() default "";

    /**
     * 是否参与查询条件，默认为 true
     *
     * @return boolean
     */
    boolean where() default true;

    /**
     * 查询条件，默认为等于
     *
     * @return query method
     */
    QueryEnum value() default QueryEnum.EQ;
}
