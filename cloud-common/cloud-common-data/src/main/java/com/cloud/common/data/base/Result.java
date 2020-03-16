package com.cloud.common.data.base;

import com.cloud.common.data.enums.ResultEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @Author Aijm
 * @Description 返回公共实现
 * @Date 2019/5/8
 */
@Slf4j
@Data
public final class Result<T> implements Serializable {

    private static final long serialVersionUID = -5816713866887895850L;
    /**
     * 错误码
     */
    private Integer code = ResultEnum.ERROR.getCode();

    /**
     * 错误信息
     */
    private String msg = null;

    /**
     * 返回结果实体
     */
    private T data = null;

    public Result() {
    }

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> error(String msg) {
        log.debug("返回默认错误：, msg={}",  msg);
        return new Result<>(ResultEnum.ERROR.getCode(), msg, null);
    }

    public static <T> Result<T> error(ResultEnum resultEnum) {
        log.debug("返回错误：code={}, msg={}", resultEnum.getCode(), resultEnum.getDesc());
        return new Result<>(resultEnum.getCode(), resultEnum.getDesc(), null);
    }

    public static <T> Result<T> error(int code, String msg) {
        log.debug("返回错误：code={}, msg={}", code, msg);
        return new Result<>(code, msg, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), "", data);
    }


}
