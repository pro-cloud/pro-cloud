package com.cloud.common.oss.entity;

import lombok.Data;

import java.io.Serializable;

/**
 *  回调传递参数
 *  belongName/belongType/prePath/yyyyMMdd/HHmmss/上传文件名
 * @author Aijm
 * @since 2019/9/11
 */
@Data
public class CallBack implements Serializable {
    private static final long serialVersionUID=1L;


    /**
     * oss文件的路径存储地址
     */
    private String filePath;

    /**
     * 文件地址前缀
     */
    private String prePath;

    /**
     * 归属应用
     */
    private String belongName;

    /**
     * 归属应用类别
     */
    private String belongType;

    /**
     * 文件的大小
     */
    private long size;
    private String mimeType;

    private String width;
    private String height;


}
