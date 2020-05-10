package com.cloud.auth.component;

import com.cloud.common.data.base.Result;
import com.cloud.common.data.enums.ResultEnum;
import com.cloud.common.data.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

import java.io.Serializable;


/**
 * 授权服务器 异常统一处理
 * @author Aijm
 * @since 2019/5/16
 */
@Slf4j
public class Auth2ResponseExceptionTranslator implements WebResponseExceptionTranslator {

    @Override
    public ResponseEntity translate(Exception e) {
        log.error("授权服务器 异常统一处理:异常:{}", e.getMessage(), e);
        if (e instanceof InvalidTokenException){
            Result<Serializable> error = Result.error(ResultEnum.TOKEN_PAST);
            return new ResponseEntity(error, HttpStatus.OK);
        } else if (e instanceof UsernameNotFoundException) {
            Result<Serializable> error = Result.error(ResultEnum.LOGIN_NAME);
            return new ResponseEntity(error, HttpStatus.OK);
        } else if (e instanceof InvalidGrantException) {
            Result<Serializable> error = Result.error(ResultEnum.LOGIN_PASSWORD);
            return new ResponseEntity(error, HttpStatus.OK);
        } else if (e instanceof BadCredentialsException) {
            Result<Serializable> error = Result.error(ResultEnum.ERROR);
            return new ResponseEntity(error, HttpStatus.OK);
        }
        if(e.getCause() instanceof BaseException) {
            Result<Serializable> error = Result.error(((BaseException) e.getCause()).getCode(), e.getCause().getMessage());
            return new ResponseEntity(error, HttpStatus.OK);
        }
        return new ResponseEntity(Result.error(""), HttpStatus.OK);
    }
}
