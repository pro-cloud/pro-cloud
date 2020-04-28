package com.cloud.common.data.base;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.common.data.util.TreeUtil;
import com.cloud.common.entity.TreeEntity;

/**
 * @Author Aijm
 * @Description 封装ServiceImpl
 * @Date 2019/10/11
 */
public class TreeService<M extends BaseMapper<T>, T extends TreeEntity> extends BaseService<M, T> implements IProService<T> {


    @Override
    public boolean save(T entity) {
        if (StrUtil.isBlank(entity.getParentIds())) {
            if (TreeUtil.ROOT_PID.equals(entity.getParentId())|| entity.getParentId() == null) {
                entity.setParentIds(TreeUtil.ROOT_PID+",");
                entity.setParentId(TreeUtil.ROOT_PID);
            } else {
                T t = baseMapper.selectById(entity.getParentId());
                entity.setParentIds(t.getParentIds()+t.getId()+",");
            }
        }
        return super.save(entity);
    }

}
