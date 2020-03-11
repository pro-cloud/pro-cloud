package com.cloud.common.data.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.URLUtil;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  客户端文件下载工具类
 * @author Aijm
 * @since 2019/9/22
 */
@UtilityClass
public class FileDownUtil {


    /**
     *  输出到客户端
     * @param response
     * @param fileName
     * @param data
     * @throws IOException
     */
    public static void write(HttpServletResponse response, String fileName, byte[] data) throws IOException {
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="+ URLUtil.encode(fileName));
        IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
    }
}
