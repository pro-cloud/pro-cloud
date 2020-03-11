package com.cloud.common.oss.entity;

import lombok.Data;

import java.io.Serializable;

/**
 *  文件下载的参数
 * @author Aijm
 * @since 2019/9/22
 */
@Data
public class FileDown implements Serializable {

    private static final long serialVersionUID=1L;
    /**
     * 请求下载的路径集合 多文件以, 分割 和ids 只能有一个出现
     *      当两个都出现时 优先处理urls
     */
    private String urls;

    /**
     * 请求下载的文件的id集合 多文件以, 分割
     */
    private String ids;

    /**
     * 下载类型  0 直接下载(多文件只下载第一个文件); 1 zip 下载
     *          默认zip下载 ( 和 TYPE_FILE TYPE_ZIP 结合使用)
     */
    private String type;

    /**
     * 下载时的文件名
     *   zip 默认为yyyyMMddHHmmss.zip;  直接下载默认为文件的名称（路径（/）后的名称）
     */
    private String fileName;

    /**
     *  下载的类型 0 直接下载; 1 打包下载
     */
    public static final String TYPE_FILE = "0";
    public static final String TYPE_ZIP = "1";
}
