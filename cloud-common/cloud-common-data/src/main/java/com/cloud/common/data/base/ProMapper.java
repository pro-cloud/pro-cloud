package com.cloud.common.data.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.common.entity.BaseEntity;

/**
 * @Author Aijm
 * @Description 接口baseMapper
 * @Date 2019/10/12
 */
public interface ProMapper<T extends BaseEntity> extends BaseMapper<T> {

}
