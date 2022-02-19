package com.batata.crud.servie;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.batata.crud.annotation.Query;
import com.batata.crud.constant.CurdConstant;
import com.batata.crud.dto.BaseDTO;
import com.batata.crud.entity.BaseEntity;
import com.batata.crud.mapper.BatataBaseMapper;
import com.batata.crud.param.BaseParam;

import java.lang.reflect.Field;
import java.util.*;

/**
 * <p>
 * 基础应用接口，使用 mybatis plus 实现
 * </p>
 *
 * @author czj
 * @since 2022-02-19
 **/
public interface BatataBaseService<P extends BaseParam, T extends BaseEntity, D extends BaseDTO> {

    /**
     * 获取实体的 mapper
     *
     * @return mapper
     */
    BatataBaseMapper<T> getMapper();

    /**
     * 分页查询
     *
     * @param param 查询参数
     *
     * @return 分页结果
     */
    default IPage<T> page(P param) {

        return page(param, null);
    }

    /**
     * 分页查询
     *
     * @param param 查询参数
     * @param wrapper 自定义查询字段及查询条件
     *
     * @return 分页结果
     */
    default IPage<T> page(P param, Wrapper<T> wrapper) {

        Page<T> page = new Page<>(param.getPageNo(), param.getPageSize());

        // 排序
        OrderItem[] orderItems = param.getOrderItems();
        if (orderItems != null) {
            page.addOrder(orderItems);
        }

        // 若未自定义查询，则根据查询条件生成查询
        if (wrapper == null) {
            wrapper = getWrapper(param);
        }

        return getMapper().selectPage(page, wrapper);
    }

    /**
     * 根据提供的查询条件，组装查询条件，用户可重写此方法
     *
     * @param param 查询条件
     *
     * @return QueryWrapper
     */
    @SuppressWarnings("unchecked")
    default QueryWrapper<T> getWrapper(P param) {

        QueryWrapper<T> queryWrapper = new QueryWrapper<>();

        // 查询条件字段对象集合
        Field[] fields = param.getClass().getDeclaredFields();
        // 拼接查询条件
        Arrays.stream(fields)
                .filter(field -> {
                    // 判断字段是否参与查询
                    if (field.isAnnotationPresent(Query.class)) {
                        Query query = field.getAnnotation(Query.class);
                        return query.where();
                    }
                    return true;
                })
                .forEach(field -> {
                    try {
                        // 用于标记字段是否参与查询
                        boolean isNotJoinSearch;

                        field.setAccessible(true);

                        // 字段名
                        String column;
                        if (field.isAnnotationPresent(Query.class) && StringUtils.isNotBlank(field.getAnnotation(Query.class).column())) {
                            // 用户指定了数据库中对应的字段
                            column = field.getAnnotation(Query.class).column();
                        } else {
                            // 直接使用参数字段名 (逆驼峰)
                            column = StringUtils.camelToUnderline(field.getName());
                        }

                        // 判断对应字段是否存在值

                        if (field.get(param) instanceof String) {
                            isNotJoinSearch = StringUtils.isBlank((CharSequence) field.get(param));
                        } else {
                            isNotJoinSearch = Objects.isNull(field.get(param));
                        }

                        if (isNotJoinSearch) {
                            return;
                        }

                        // 判断用户是否指定了查询条件，若未指定则默认使用等于查询
                        if (field.isAnnotationPresent(Query.class)) {
                            switch (field.getAnnotation(Query.class).value()) {
                                case GE:
                                    queryWrapper.ge(column, field.get(param));
                                    break;
                                case GT:
                                    queryWrapper.gt(column, field.get(param));
                                    break;
                                case LE:
                                    queryWrapper.le(column, field.get(param));
                                    break;
                                case LT:
                                    queryWrapper.lt(column, field.get(param));
                                    break;
                                case BETWEEN:
                                    if (field.get(param).getClass().isArray()) {
                                        Object[] objects = (Object[]) field.get(param);
                                        if (objects.length == 2) {
                                            queryWrapper.between(column, objects[0], objects[1]);
                                        }
                                    } else if (field.get(param) instanceof List) {
                                        List<Object> list = (List<Object>) field.get(param);
                                        if (list.size() == 2) {
                                            queryWrapper.between(column, list.get(0), list.get(1));
                                        }
                                    } else if (field.get(param) instanceof String) {
                                        String[] split = field.get(param).toString().split(CurdConstant.COMMA);
                                        if (split.length == 2) {
                                            queryWrapper.between(column, split[0], split[1]);
                                        }
                                    }
                                    break;
                                case IN:
                                    if(field.get(param).getClass().isArray()){
                                        Object[] objects = (Object[]) field.get(param);
                                        queryWrapper.in(column, Arrays.asList(objects));
                                    }else if(field.get(param) instanceof List){
                                        queryWrapper.in(column, (List)field.get(param));
                                    }else if(field.get(param) instanceof String){
                                        String[] split = field.get(param).toString().split(CurdConstant.COMMA);
                                        queryWrapper.in(column, Arrays.asList(split));
                                    }
                                    break;
                                case LIKE:
                                    if(field.get(param) instanceof String){
                                        String value = field.get(param).toString();
                                        if(value.contains(CurdConstant.PERCENT)){
                                            value = value.replace(CurdConstant.PERCENT, "\\%");
                                        }
                                        queryWrapper.like(column, value);
                                    }
                                    break;
                                default:
                                    queryWrapper.eq(column, field.get(param));
                            }
                        }else {
                            queryWrapper.eq(column, field.get(param));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        return queryWrapper;
    }
}
