package com.cloud.common.data.util.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Aijm
 * @Description Zookeeper 配置项
 * @Date 2019/8/19
 */
@Data
@Component
@ConfigurationProperties(prefix = "snowflake")
public class SnowflakeProps {
    /**
     * 终端ID
     */
    private int workerId = 1;

    /**
     * 数据中心ID
     */
    private int dataId = 1;
}
