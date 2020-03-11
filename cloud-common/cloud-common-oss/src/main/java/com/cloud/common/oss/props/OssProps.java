package com.cloud.common.oss.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @Author Aijm
 * @Description oss 配置项
 * @Date 2019/8/19
 */
@Data
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



}
