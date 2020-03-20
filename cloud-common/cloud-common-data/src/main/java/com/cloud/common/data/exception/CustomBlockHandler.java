package com.cloud.common.data.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.cloud.common.data.base.Result;
import com.cloud.common.data.enums.ResultEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Aijm
 * @Description 自定义sentinel异常返回信息
 * @Date 2020/3/20
 */
@Slf4j
@Component
public class CustomBlockHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, BlockException ex) throws Exception {
        log.error("CustomBlockHandler异常:{}", ex.getMessage());
        Result result;
        // 根据异常类型区分违反哪种规则
        if (ex instanceof FlowException) {
            // 限流异常
            result = Result.error(ResultEnum.SENTINEL_DEGRADE_ERROR);
        } else if (ex instanceof DegradeException) {
            // 降级异常
            result = Result.error(ResultEnum.SENTINEL_DEGRADE_ERROR);
        } else if (ex instanceof ParamFlowException) {
            // 热点参数异常
            result = Result.error(ResultEnum.SENTINEL_PARAM_ERROR);
        } else if (ex instanceof SystemBlockException) {
            // 系统规则异常
            result = Result.error(ResultEnum.SENTINEL_SYSTEM_ERROR);
        } else if (ex instanceof AuthorityException) {
            // 认证异常
            result = Result.error(ResultEnum.SENTINEL_AUTHORITY_ERROR);
        } else {
            result = Result.error(ResultEnum.SENTINEL_ERROR);
        }
        response.setStatus(500);
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.setContentType("application/json;charset=utf-8");
        new ObjectMapper().writeValue(response.getWriter(), result);
    }
}