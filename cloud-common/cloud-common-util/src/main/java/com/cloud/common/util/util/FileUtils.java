package com.cloud.common.util.util;

import cn.hutool.core.io.FileUtil;
import com.google.common.collect.Maps;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.util.Map;

/**
 * @Author Aijm
 * @Description 文件工具类
 *   文件类型需要存入到字典库中和代码想对应
 * @Date 2019/9/23
 */
@UtilityClass
public class FileUtils extends FileUtil {

    /**
     * 视频类型对应的值
     */
    public static final Integer VIDEO_TYPE = 0;

    /**
     * 音频类型对应的值
     */
    public static final Integer AUDIO_TYPE = 1;

    /**
     * zip类型对应的值
     */
    public static final Integer ZIP_TYPE = 2;

    /**
     * doc类型对应的值
     */
    public static final Integer DOC_TYPE = 3;

    /**
     * 图片类型对应的值
     */
    public static final Integer IMAGE_TYPE = 4;

    /**
     * 其他类型对应的值
     */
    public static final Integer OTHER_TYPE = 9;

    /**
     * 视频类型
     */
    private static final Map<String, String> VIDEO_TYPE_MAP = Maps.newHashMap();
    static {
        VIDEO_TYPE_MAP.put("mp4", "mp4");
        VIDEO_TYPE_MAP.put("flv", "flv");
        VIDEO_TYPE_MAP.put("rm", "rm");
        VIDEO_TYPE_MAP.put("rmvb", "rmvb");
        VIDEO_TYPE_MAP.put("wmv", "wmv");
    }

    /**
     * 音频类型
     */
    private static final Map<String, String> AUDIO_TYPE_MAP = Maps.newHashMap();
    static {
        VIDEO_TYPE_MAP.put("mp3", "mp3");
        VIDEO_TYPE_MAP.put("wav", "wav");
        VIDEO_TYPE_MAP.put("avi", "avi");
    }

    /**
     * 压缩包类型
     */
    private static final Map<String, String> ZIP_TYPE_MAP = Maps.newHashMap();
    static {
        ZIP_TYPE_MAP.put("zip", "zip");
        ZIP_TYPE_MAP.put("rar", "rar");
        ZIP_TYPE_MAP.put("tar", "tar");
    }

    /**
     * 文档类型
     */
    private static final Map<String, String> DOC_TYPE_MAP = Maps.newHashMap();
    static {
        DOC_TYPE_MAP.put("doc", "doc");
        DOC_TYPE_MAP.put("docx", "docx");
        DOC_TYPE_MAP.put("pdf", "pdf");
        DOC_TYPE_MAP.put("txt", "txt");
        DOC_TYPE_MAP.put("csv", "csv");
        DOC_TYPE_MAP.put("pptx", "pptx");
        DOC_TYPE_MAP.put("xlsx", "xlsx");
        DOC_TYPE_MAP.put("xls", "xls");
    }

    /**
     * 图片类型
     */
    private static final Map<String, String> IMAGE_TYPE_MAP = Maps.newHashMap();
    static {
        IMAGE_TYPE_MAP.put("jpg", "jpg");
        IMAGE_TYPE_MAP.put("png", "png");
        IMAGE_TYPE_MAP.put("gif", "gif");
    }


    /**
     * 根据文件类型获取对应文件的类型
     *     优先把容易匹配的放在判断前面
     * @param fileSuffix 文件后缀 不带.
     * @return 文件类型值
     */
    public static Integer getFileType(String fileSuffix) {
        if (FileUtils.DOC_TYPE_MAP.get(fileSuffix) != null) {
            return DOC_TYPE;
        } else if (FileUtils.IMAGE_TYPE_MAP.get(fileSuffix) != null) {
            return IMAGE_TYPE;
        } else if (FileUtils.ZIP_TYPE_MAP.get(fileSuffix) != null) {
            return ZIP_TYPE;
        } else if (FileUtils.AUDIO_TYPE_MAP.get(fileSuffix) != null) {
            return AUDIO_TYPE;
        } else if (FileUtils.VIDEO_TYPE_MAP.get(fileSuffix) != null) {
            return VIDEO_TYPE;
        }
        return OTHER_TYPE;
    }


    /**
     * 根据文件名称 获取文件后缀
     * @param fileName
     * @return
     */
    public static String getFileSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }

    /**
     * 根据文件 获取文件后缀
     * @param file
     * @return
     */
    public static String getFileSuffix(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }

}
