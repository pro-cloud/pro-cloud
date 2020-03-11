package com.cloud.common.data.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.cloud.common.data.util.props.SnowflakeProps;

/**
 *  snowflake 算法获取id
 * @author Aijm
 * @since 2019/8/24
 */
public class IdUtils extends IdUtil {

    private static final SnowflakeProps sfProps = SpringUtil.getBean(SnowflakeProps.class);

    /**
     * 分布式系统中 获取id
     * @return
     */
    public static long getNextId() {
        Snowflake snowflake = cn.hutool.core.util.IdUtil.createSnowflake(sfProps.getWorkerId(), sfProps.getDataId());
        return snowflake.nextId();
    }
}
