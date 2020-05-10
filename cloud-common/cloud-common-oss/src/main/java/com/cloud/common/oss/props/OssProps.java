package com.cloud.common.oss.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


/**
 * @Author Aijm
 * @Description oss 配置项
 * @Date 2019/8/19
 */
@Data
@Configuration
@RefreshScope
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProps {


    /**
     * accessKeyId
     */
    private String accessKeyId;
    /**
     *
     */
    private String accessKeySecret;

    private String bucketName;

    private String callback;
    private String endpoint;
    /**
     * 内网
     */
    private String endpointInternal;

    private long maxSize;
    /**
     * 代理过期时间 默认分钟
     */
    private long policyExpire;


    /**
     * 生成访问链接时间
     */
    private long expire;



}
