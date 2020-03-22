package com.cloud.common.data.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Aijm
 * @since 2020/3/22
 */
@Configuration
public class MeterRegistryConfig {

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String application){
        return registry -> registry.config().commonTags("application", application);
    }
}
