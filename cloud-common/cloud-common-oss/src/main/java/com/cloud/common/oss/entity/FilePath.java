package com.cloud.common.oss.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Aijm
 * @Description 属性文件
 *  最终的目录为  belongName/belongType/prePath/yyyyMMdd/HH
 * @Date 2019/9/12
 */
@Data
public class FilePath implements Serializable {
    private static final long serialVersionUID=1L;


    /**
     * 文件上传类型 唯一字段
     * 可以认为 是唯一id的标记
     */
    private String id;

    /**
     * 归属应用
     */
    private String belongName;

    /**
     * 文件地址前缀
     */
    private String prePath;

    /**
     * 归属应用类别
     */
    private String belongType;

    /**
     * 回调地址
     */
    private String callback;
}
