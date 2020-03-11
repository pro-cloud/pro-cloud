package com.cloud.common.oss.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.cloud.common.oss.props.OssProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Aijm
 * @Description  OSS 配置类
 * @Date 2019/8/19
 */
@Configuration
@EnableConfigurationProperties(OssProps.class)
public class OssConfig {

    private final OssProps ossProps;

    @Autowired
    public OssConfig(OssProps ossProps) {
        this.ossProps = ossProps;
    }

    @Bean
    public OSS ossInnerClient() {
        return new OSSClientBuilder().build(ossProps.getEndpointInternal(),
                ossProps.getAccessKeyId(), ossProps.getAccessKeySecret());
    }

    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder().build(ossProps.getEndpoint(),
                ossProps.getAccessKeyId(), ossProps.getAccessKeySecret());
    }

}
