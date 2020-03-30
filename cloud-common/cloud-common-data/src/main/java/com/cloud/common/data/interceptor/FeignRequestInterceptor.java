package com.cloud.common.data.interceptor;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * feign 的head 传递
 * @author Aijm
 * @since 2019/8/4
 */
@Component
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {

    /**
     * 对feign 请求手动的传递head
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info("对feign 请求进行拦截");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request= attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                requestTemplate.header(name, values);
            }
        }
    }
}
