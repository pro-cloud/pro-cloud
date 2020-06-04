package com.cloud.common.data.exception;

import com.cloud.common.data.base.Result;
import com.cloud.common.data.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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
    public Result handleBasicException(BaseException e){
        log.error("抛异常code:{}，msg:{}", e.getCode(), e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 所有验证框架异常捕获处理
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result handleValidException(Exception e){
        BindingResult bindResult = null;
        if (e instanceof BindException) {
            bindResult = ((BindException) e).getBindingResult();
        } else if (e instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) e).getBindingResult();
        }
        String msg;
        if (bindResult != null && bindResult.hasErrors()) {
            msg = bindResult.getAllErrors().get(0).getDefaultMessage();
        }else {
            msg = "系统繁忙，请稍后重试...";
        }
        log.error("参数校验异常，msg:{}",  e.getMessage(), e);
        return Result.error(ResultEnum.CRUD_VALID_NOT.getCode(), msg);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleException(Exception e) {
        log.error("Exception全局异常信息 ex={}", e.getMessage(), e);
        return Result.error(e.getLocalizedMessage());
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleHttpRequestMethodNotSupportedException(Exception e) {
        log.error("HttpRequestMethodNotSupportedException异常信息 ex={}", e.getMessage(), e);
        return Result.error(ResultEnum.SYSTEM_REQUEST_METHOD_NOT_SUPPORTED);
    }
}
