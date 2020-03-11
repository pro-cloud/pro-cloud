package com.cloud.gateway;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CloudGatewayApplicationTest {



    public  void test(){
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);

        //图形验证码写出，可以写出到文件，也可以写出到流
          lineCaptcha.write("d:/line.png");
        //输出code
         log.info(lineCaptcha.getCode());
        //验证图形验证码的有效性，返回boolean值
        boolean verify = lineCaptcha.verify("1234");

        //重新生成验证码
                lineCaptcha.createCode();
                lineCaptcha.write("d:/line.png");
        //新的验证码
        log.info(lineCaptcha.getCode());
        //验证图形验证码的有效性，返回boolean值
        boolean verify1 = lineCaptcha.verify("1234");
    }
}
