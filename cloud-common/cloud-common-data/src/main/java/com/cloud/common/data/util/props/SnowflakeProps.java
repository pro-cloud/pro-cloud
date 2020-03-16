package com.cloud.common.data.util.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Aijm
 * @Description snowflake 配置项
 * @Date 2019/8/19
 */
@Data
@RefreshScope
@Configuration
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
