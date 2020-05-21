package com.cloud.common.oss.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.URLUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.cloud.common.data.util.SpringUtil;
import com.cloud.common.oss.props.OssProps;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * @Author Aijm
 * @Description  oss oss文件下载 工具
 * @Date 2019/9/24
 */
@Slf4j
@UtilityClass
public class FileOssDownUtil {

    private static OssProps ossProps = SpringUtil.getBean("ossProps");

    private static OSS ossInnerClient = SpringUtil.getBean("ossInnerClient");


    /**
     * 下载oss文件到本地
     * @param ossFile oss文件的路径
     * @param localFile  本地存储的路径
     */
    public static void downFile(String ossFile, String localFile){
        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossInnerClient.getObject(new GetObjectRequest(ossProps.getBucketName(), ossFile), new File(localFile));
    }


    /**
     *  下载多个oss文件并进行压缩
     * @param ossFiles oss 文件的路径
     * @return 压缩后的二进制
     */
    public static byte[] writeZip(List<String> ossFiles){

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String ossFile : ossFiles) {
            OSSObject ossObject = ossInnerClient.getObject(ossProps.getBucketName(), ossFile);
            InputStream in = ossObject.getObjectContent();
            byte[] bytes = IoUtil.readBytes(in);
            IoUtil.write(zip, CharsetUtil.UTF_8, false, bytes);
        }
        IoUtil.close(zip);
        return outputStream.toByteArray();
    }

    /**
     *  下载单个oss文件并进行压缩
     * @param ossFile  oss文件的路径
     * @return 压缩后的二进制
     */
    public static byte[] writeZip(String ossFile){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        OSSObject ossObject = ossInnerClient.getObject(ossProps.getBucketName(), ossFile);
        InputStream in = ossObject.getObjectContent();
        byte[] bytes = IoUtil.readBytes(in);
        IoUtil.write(zip, CharsetUtil.UTF_8, true, bytes);
        return outputStream.toByteArray();
    }

    /**
     *  输出到客户端
     * @param response 输出的流
     * @param fileName 输出的文件名
     * @param ossFile oss文件的路径
     * @throws IOException
     */
    public static void writeZip(HttpServletResponse response, String fileName, String ossFile) throws IOException {
        byte[] bytes = writeZip(ossFile);
        // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="+ URLUtil.encode(fileName));
        IoUtil.write(response.getOutputStream(), Boolean.TRUE, bytes);
    }

    /**
     *  输出到客户端
     * @param response 输出的流
     * @param fileName 输出的文件名
     * @param ossFile oss文件的路径
     * @throws IOException
     */
    public static void write(HttpServletResponse response, String fileName, String ossFile) throws IOException {
        OSSObject ossObject = ossInnerClient.getObject(ossProps.getBucketName(), ossFile);
        InputStream in = ossObject.getObjectContent();
        byte[] bytes = IoUtil.readBytes(in);
        // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="+ URLUtil.encode(fileName));
        IoUtil.write(response.getOutputStream(), Boolean.TRUE, bytes);
    }
}
