package com.batata.crud.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.batata.crud.base.BaseResult;
import com.batata.crud.dto.BaseDTO;
import com.batata.crud.dto.RequestBodyDTO;
import com.batata.crud.entity.BaseEntity;
import com.batata.crud.param.BaseParam;
import com.batata.crud.servie.BatataBaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 基础 controller 类，提供基础的增删改查及分页接口
 * </p>
 *
 * @author czj
 * @since 2022-02-19
 **/
public abstract class BaseController<P extends BaseParam, T extends BaseEntity, D extends BaseDTO> {

    /**
     * 获取实际服务类
     *
     * @return real service
     */
    protected abstract BatataBaseService<P, T, D> getService();

    /**
     * 获取实际实体
     *
     * @return real entity
     */
    protected abstract T getEntity();

    /**
     * 获取实际 DTO
     *
     * @return real dto
     */
    protected abstract D getDTO();

    /**
     * 分页查询
     *
     * @param body 请求体
     *
     * @return 分页结果
     */
    @PostMapping("page")
    public BaseResult<IPage<T>> page(@RequestBody RequestBodyDTO<P> body) {

        return BaseResult.success(getService().page(body.getData()));
    }
}
