package com.batata.crud.param;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.batata.crud.constant.CurdConstant;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 基础参数类
 * </p>
 * <p>
 * <p>
 * 用户可通过扩展此基础类，自定义查询条件(目前暂只用于分页查询)。
 * 继承此类后，每个字段默认参与查询条件，且查询条件自动被标记为<em>"等于"<em/>查询，
 * 若用户想指定某个字段的<em>查询方式<em/>或<em>不参与查询</em>，可在对应字段上使用 {@link com.batata.crud.annotation.Query} 注解，
 * 目前暂只支持有限的查询条件，见{@link com.batata.crud.enums.QueryEnum}
 *
 * @author czj
 * @since 2022-02-19
 **/
public abstract class BaseParam {

    /**
     * 分页页码
     */
    protected Integer pageNo = 1;

    /**
     * 分页大小
     */
    protected Integer pageSize = 10;

    /**
     * 排序字段，若存在多个则由 "," 分隔，两字段间不要存在空格等字符
     */
    protected String order;

    /**
     * 排序方式，若存在多个则由 "," 分隔，两字段间不要存在空格等字符。
     */
    protected String sort;

    /**
     * 获取排序对象数组
     * <p>
     * 若排序方式的个数和排序字段的个数不相符:
     * 1. 排序方式个数 > 排序字段个数: 忽略多余的排序方式
     * 2. 排序方式个数 < 排序字段个数: 根据排序字段、方式索引一一对应的方式，不足的部分由正序补充
     *
     * @return OrderItems
     */
    public OrderItem[] getOrderItems() {
        if (StringUtils.isBlank(this.order)) {
            return null;
        }

        // 排序字段数组
        String[] orders = this.order.split(",");
        // 排序方式数组
        String[] sorts;
        if (StringUtils.isBlank(this.sort)) {
            // 若排序方式不存在，则默认填充为正序
            sorts = new String[orders.length];
            for (int i = 0; i < orders.length; ++i) {
                sorts[i] = CurdConstant.ASC;
            }
        } else {
            sorts = this.sort.split(CurdConstant.COMMA);
            if (sorts.length < orders.length) {
                String[] tempSorts = new String[orders.length];
                // 补齐不足的长度
                System.arraycopy(sorts, 0, tempSorts, 0, sorts.length);
                for (int i = sorts.length - 1; i < orders.length; ++i) {
                    tempSorts[i] = CurdConstant.ASC;
                }
                sorts = tempSorts;
            }
        }

        // 创建 orderItem 对象
        OrderItem[] orderItems = new OrderItem[orders.length];
        for (int i = 0; i < order.length(); ++i) {
            OrderItem item = new OrderItem();
            item.setColumn(com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(orders[i]));
            item.setAsc(CurdConstant.ASC.equals(sorts[i]));
            orderItems[i] = item;
        }

        return orderItems;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
