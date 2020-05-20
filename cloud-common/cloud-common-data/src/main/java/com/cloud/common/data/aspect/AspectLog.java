package com.cloud.common.data.aspect;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.data.entity.SysLog;
import com.cloud.common.data.user.SystemService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author Aijm
 * @Description 普通日志切面
 * @Date 2020/5/18
 */
@Aspect
@AllArgsConstructor
@Slf4j
@Component
public class AspectLog {

    private final SystemService systemService;

    @Pointcut("execution (* com.cloud.*.service.impl.*Impl.*(..))")
    private void pointcut() {
    }

    @SneakyThrows
    @Around("pointcut()")
    public Object saveSysLog(ProceedingJoinPoint point) {

        SysLog sysLog = new SysLog();
        // 请求的参数
        String params = Arrays.toString(point.getArgs());
        sysLog.setContent(params);
        sysLog.setUserId(systemService.getUserId());
        Long startTime = System.currentTimeMillis();
        Object obj = point.proceed();
        Long endTime = System.currentTimeMillis();
        sysLog.setTime(endTime - startTime);
        sysLog.setResult(JSONObject.toJSONString(obj));
        log.info("切面日志:{}", JSONObject.toJSONString(sysLog));
        return obj;
    }

}
