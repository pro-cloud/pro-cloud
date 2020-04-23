package com.cloud.common.data.util;

import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Aijm
 * @Description 获取request等
 * @Date 2020/4/23
 */
@UtilityClass
public class ServletUtil {

    /**
     * 得到request对象
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return request;
    }

    /**
     * 得到response对象
     *
     * @return
     */
    public HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();

        return response;
    }
}
