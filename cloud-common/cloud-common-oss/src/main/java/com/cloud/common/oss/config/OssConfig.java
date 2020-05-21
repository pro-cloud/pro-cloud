package com.cloud.common.oss.config;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.cloud.common.oss.props.OssProps;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Aijm
 * @Description  OSS 配置类
 * @Date 2019/8/19
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(OssProps.class)
public class OssConfig {

    private final OssProps ossProps;


    @Bean
    public OSS ossInnerClient() {
        // 创建ClientConfiguration实例，按照您的需要修改默认参数。
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        // 开启支持CNAME。CNAME是指将自定义域名绑定到存储空间上。
        conf.setSupportCname(true);
        return new OSSClientBuilder().build(ossProps.getEndpointInternal(),
                ossProps.getAccessKeyId(), ossProps.getAccessKeySecret(), conf);
    }

    @Bean
    public OSS ossClient() {
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        conf.setSupportCname(true);
        return new OSSClientBuilder().build(ossProps.getEndpoint(),
                ossProps.getAccessKeyId(), ossProps.getAccessKeySecret(), conf);
    }

}
