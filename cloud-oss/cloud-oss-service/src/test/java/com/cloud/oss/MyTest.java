package com.cloud.oss;

import cn.hutool.core.io.FileUtil;
import com.aliyun.oss.OSS;
import com.cloud.common.oss.props.OssProps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.net.URL;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MyTest {

    @Autowired
    private OSS ossClient;

    @Autowired
    private OSS ossInnerClient;

    @Autowired
    private OssProps ossProps;

    @Test
    public void aa() {
//        PutObjectResult putObjectResult = ossClient.putObject(ossProps.getBucketName(), "201909201555/line.png", new File("d:/line.png"));

        // 设置URL过期时间为1小时。
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
// 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(ossProps.getBucketName(), "201909201555/line.png", expiration);
        log.info("22");

    }

    @Test
    public void path() {
        String name = FileUtil.getName("usr/lo/2.jpg");
        log.info("name:{}", name);
    }

}
