package com.cloud.common.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 日志记录
 * @author Aijm
 * @since 2019/10/9
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 操作用户
     */
    private Long userId;
    /**
     * 操作
     */
    private String operation;

    /**
     * 参数内容
     */
    private String content;

    /**
     * 结果内容
     */
    private String result;

    /**
     * 方法执行时间
     */
    private Long time;

}
