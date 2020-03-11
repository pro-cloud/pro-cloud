package com.cloud.common.data.config;

import com.cloud.common.util.util.DateUtils;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * jackson序列化
 * @author Aijm
 * @since 2019/11/18
 */
@Configuration
public class JacksonConfig {

    @Bean
    @Primary
    public Jackson2ObjectMapperBuilderCustomizer builderCustomizer() {
        return builder ->
            builder.serializerByType(Long.class, ToStringSerializer.instance)
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateUtils.DATETIME_FORMATTER))
                .serializerByType(LocalDate.class, new LocalDateSerializer(DateUtils.DATE_FORMATTER))
                .serializerByType(LocalTime.class, new LocalTimeSerializer(DateUtils.TIME_FORMDAY))
                .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateUtils.DATETIME_FORMATTER))
                .deserializerByType(LocalDate.class, new LocalDateDeserializer(DateUtils.DATE_FORMATTER))
                .deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateUtils.TIME_FORMDAY));

    }
}
