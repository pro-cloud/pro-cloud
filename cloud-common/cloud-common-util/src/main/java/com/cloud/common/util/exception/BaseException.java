package com.cloud.common.util.exception;

import com.cloud.common.util.enums.ResultEnum;

/**
 * 异常处理
 * @author Aijm
 * @since 2019/5/12
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /** 异常码 */
    private final int code;

    public BaseException() {
        super("系统异常");
        this.code = 99;
    }

    public BaseException(ResultEnum resultEnum) {
        super(resultEnum.getDesc());
        this.code = resultEnum.getCode();
    }

    public BaseException(String message) {
        super(message);
        this.code = 99;
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
