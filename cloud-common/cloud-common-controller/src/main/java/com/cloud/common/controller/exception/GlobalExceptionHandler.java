package com.cloud.common.controller.exception;

import com.cloud.common.util.base.Result;
import com.cloud.common.util.enums.ResultEnum;
import com.cloud.common.util.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常统一处理
 * @author Aijm
 * @since 2019/5/19
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleBasicException(BaseException baseException){
        log.error("抛异常code:{}，msg:{}", baseException.getCode(), baseException.getMessage());
        return Result.error(baseException.getCode(), "");
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleValidException(MethodArgumentNotValidException e){
        log.error("参数校验异常，msg:{}",  e.getMessage(), e);
        return Result.error(ResultEnum.CRUD_VALID_NOT);
    }

}
