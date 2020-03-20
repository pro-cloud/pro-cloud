package com.cloud.common.data.sentinel;

import cn.hutool.core.util.StrUtil;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Aijm
 * @Description 区分来源
 * @Date 2020/3/20
 */
@Component
@Slf4j
@RefreshScope
public class CustomRequestOriginParser implements RequestOriginParser {

    @Value("${spring.cloud.sentinel.origin}")
    private String origin = "Default";

    @Override
    public String parseOrigin(HttpServletRequest request) {
        // 从header中获取名为 origin 的参数并返回
        String headOrigin = request.getHeader(origin);
        if (StrUtil.isBlank(headOrigin)) {
            // 如果获取不到，则抛异常
            String err = "origin param must not be blank!";
            log.error("parse origin failed: {}", err);
            throw new IllegalArgumentException(err);
        }
        return headOrigin;
    }
}
