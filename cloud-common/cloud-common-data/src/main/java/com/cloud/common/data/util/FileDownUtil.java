package com.cloud.common.data.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.URLUtil;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletResponse;

/**
 *  客户端文件下载工具类
 * @author Aijm
 * @since 2019/9/22
 */
@UtilityClass
public class FileDownUtil {


    /**
     *  输出到客户端
     * @param fileName
     * @param data
     */
    @SneakyThrows
    public static void write(String fileName, byte[] data) {
        HttpServletResponse response = ServletUtil.getResponse();
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="+ URLUtil.encode(fileName));
        IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
    }
}
