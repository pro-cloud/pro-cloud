package com.cloud.common.data.mybatis;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.cloud.common.data.util.IdUtils;

/**
 * 自定义id生成器
 * @author Aijm
 * @since 2020/3/14
 */
public class CustomIdGenerator implements IdentifierGenerator {


    @Override
    public Number nextId(Object entity) {
        return IdUtils.getNextId();
    }
}
