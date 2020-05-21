package com.cloud.common.oss.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.*;
import com.cloud.common.data.user.SystemService;
import com.cloud.common.data.util.SpringUtil;
import com.cloud.common.oss.entity.CallBack;
import com.cloud.common.oss.entity.FilePath;
import com.cloud.common.oss.entity.SignDTO;
import com.cloud.common.oss.props.OssProps;
import com.cloud.common.data.base.Result;
import com.cloud.common.data.enums.ResultEnum;
import com.cloud.common.util.util.DateUtils;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * @Author Aijm
 * @Description oss文件上传工具类
 * @Date 2019/9/24
 */
@UtilityClass
@Slf4j
public class FileOssUploadUtil {

    private static SystemService systemService = SpringUtil.getBean(SystemService.class);
    private static OssProps ossProps = SpringUtil.getBean("ossProps");
    private static OSS ossInnerClient = SpringUtil.getBean("ossInnerClient");






    /**
     * 根据oss文件存储的路径获取到 外网访问地址
     *  默认过期时间很长
     * @param ossFile oss文件的路径
     * @return
     */
    public static String getOssFileUrl(String ossFile) {

        // 设置URL过期时间为expire  分钟。
        Date expiration = new Date(System.currentTimeMillis() + ossProps.getExpire() * 60000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossInnerClient.generatePresignedUrl(ossProps.getBucketName(), ossFile, expiration);
        return specialUrlEncode(url.toString());
    }


    /**
     * 根据MultipartFile 文件上传
     * @param file
     * @param filePath
     * @return
     */
    public static Result<CallBack> uploadMultipartFile(MultipartFile file, FilePath filePath) {

        InputStream in = null;
        try {
            in = file.getInputStream();
            String urlPath = getFileKey(filePath).concat(file.getOriginalFilename());
            PutObjectRequest putObjectRequest = new PutObjectRequest(ossProps.getBucketName(), urlPath, in);
            return getCallBackResult(filePath, putObjectRequest);
        } catch (Exception e) {
            log.info("文件上传 异常");
        } finally {
            IoUtil.close(in);
        }

        return Result.error(ResultEnum.CRUD_SAVE_FAIL);
    }

    /**
     * oss 根据 file 上传文件
     * @param file
     * @param filePath
     * @return
     */
    public static Result<CallBack> uploadFile(File file, FilePath filePath) {
        try (FileInputStream inFile = new FileInputStream(file)) {
            String fileKey = getFileKey(filePath);
            String urlPath = fileKey.concat(file.getName());
            PutObjectRequest putObjectRequest = new PutObjectRequest(ossProps.getBucketName(), urlPath, inFile);
            return getCallBackResult(filePath, putObjectRequest);
        } catch (Exception e) {
            log.error("文件上传 异常:{}", e.getMessage(), e);
        }
        return Result.error(ResultEnum.CRUD_SAVE_FAIL);
    }


    /**
     * 根据文件二进制流上传
     * @param in
     * @param fileName
     * @param filePath
     * @return
     */
    public static Result<CallBack> uploadFile(InputStream in, String fileName, FilePath filePath) {
        try {
            String fileKey = getFileKey(filePath);
            String urlPath = fileKey.concat(fileName);
            PutObjectRequest putObjectRequest = new PutObjectRequest(ossProps.getBucketName(), urlPath, in);
            return getCallBackResult(filePath, putObjectRequest);
        } catch (Exception e) {
            log.error("文件上传 异常", e);
        } finally {
            IoUtil.close(in);
        }

        return Result.error(ResultEnum.CRUD_SAVE_FAIL);
    }

    // -------------------------------------------------------------- 私有的提供上传封装

    /**
     *  设置回调和返回 回调结果
     * @param filePath
     * @param putObjectRequest
     * @return
     * @throws IOException
     */
    private static Result<CallBack> getCallBackResult(FilePath filePath, PutObjectRequest putObjectRequest) throws IOException {
        Callback callback = getCallback(filePath);
        putObjectRequest.setCallback(callback);
        PutObjectResult putObjectResult = ossInnerClient.putObject(putObjectRequest);
        byte[] buffer = new byte[1024];
        putObjectResult.getResponse().getContent().read(buffer);
        return JSON.parseObject(new String(buffer), new TypeReference<Result<CallBack>>() {});
    }

    // -------------------------------------------------------------- 公共的提供上传属性
    /**
     * 获取到上传文件应该存储的路径
     * @param filePath
     * @return
     */
    public static String getFileKey(FilePath filePath) {

        String yyyyMMdd = DateUtils.getCurrentShortDateStr();
        StringBuilder dir = new StringBuilder(filePath.getBelongName());
        dir.append("/").append(filePath.getBelongType())
                .append("/").append(filePath.getPrePath())
                .append("/").append(yyyyMMdd)
                .append("/").append(DateUtils.getCurrentTimeStr()).append("/");
        return dir.toString();
    }

    /**
     * 获取到回调的callback信息
     * @return
     */
    public static Callback getCallback(FilePath filePath) {
        String callBack = filePath.getCallback();
        if (StrUtil.isBlank(callBack)) {
            callBack = ossProps.getCallback();
        }
        Callback callback = new Callback();
        StringBuilder sbr = new StringBuilder(callBack);
        sbr.append("?belongName=").append(filePath.getBelongName())
                .append("&prePath=").append(filePath.getPrePath())
                .append("&userId=").append(systemService.getUserId())
                .append("&tenantId=").append(systemService.getUserTenantIds())
                .append("&belongType=").append(filePath.getBelongType());
        callback.setCallbackUrl(sbr.toString());
        callback.setCallbackBody("filePath=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
        callback.setCalbackBodyType(Callback.CalbackBodyType.URL);
        return callback;
    }

    /**
     * 根据类型获取到 FilePath
     * @param filePaths FileProps 中的filePaths
     * @param id  FilePath中的类型 id
     * @return
     */
    public static FilePath getFilePath(List<FilePath> filePaths, String id) {
        FilePath filePath = null;
        for (FilePath item : filePaths) {
            if (item.getId().equals(id)) {
                filePath = item;
                break;
            }
        }
        return filePath;
    }

    /**
     * 特殊符号处理
     *
     * @param value
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public static String specialUrlEncode(String value) {
        return value.replace("+", "%2B").replace(" ", "%20");
    }


    /**
     * 签名生成
     * @return
     */
    public static SignDTO policy(FilePath filePath) {

        SignDTO signDTO = new SignDTO();
        // 存储目录

        String dir = FileOssUploadUtil.getFileKey(filePath);

        // 签名有效期
        long expireEndTime = System.currentTimeMillis() + ossProps.getPolicyExpire() * 600000;
        Date expiration = new Date(expireEndTime);
        // 文件大小
        long maxSize = ossProps.getMaxSize() * 1024 * 1024;
        // 回调
        Callback callback = FileOssUploadUtil.getCallback(filePath);

        // 提交节点
        String action = "http://" + ossProps.getBucketName() + "." + ossProps.getEndpoint();
        try {
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String postPolicy = ossInnerClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String policy = BinaryUtil.toBase64String(binaryData);
            String signature = ossInnerClient.calculatePostSignature(postPolicy);
            String callbackData = BinaryUtil.toBase64String(JSONUtil.toJsonStr(callback).getBytes("utf-8"));
            // 返回结果
            signDTO = SignDTO.builder().accessKeyId(ossProps.getAccessKeyId())
                    .policy(policy).signature(signature).dir(dir)
                    .callback(callbackData).action(action).expire(expiration).build();

        } catch (Exception e) {
            log.error("签名生成失败", e);
        }

        return signDTO;
    }

}
