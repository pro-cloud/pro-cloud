package com.cloud.common.security.exception;

import com.cloud.common.data.base.Result;
import com.cloud.common.data.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * security 异常统一处理
 * @author Aijm
 * @since 2020/5/06
 */
@RestControllerAdvice
@Slf4j
public class SecurityException {


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleAccessDeniedException(AccessDeniedException e) {
        log.error("拒绝授权异常信息 ex={}",e.getMessage(), e);
        return Result.error(ResultEnum.CRUD_NOT_OPERATE);
    }

}
