package com.batata.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.batata.crud.entity.BaseEntity;

/**
 * <p>
 *  基础 mapper
 * </p>
 *
 * @author czj
 * @since 2022-02-19
 **/
public interface BatataBaseMapper<T extends BaseEntity> extends BaseMapper<T> {
}
