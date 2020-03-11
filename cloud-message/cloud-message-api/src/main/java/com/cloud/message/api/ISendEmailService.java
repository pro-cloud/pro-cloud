package com.cloud.message.api;


import com.cloud.common.util.base.Result;
import com.cloud.common.util.client.CloudServiceList;
import com.cloud.message.beans.dto.SendEmailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @Author Aijm
 * @Description  feign 邮件暴露接口
 * @Date 2019/9/11
 */
@FeignClient(value = CloudServiceList.CLOUD_MESSAGE)
public interface ISendEmailService {


    /**
     * 发送邮件
     * @param sendEmail
     * @return
     */
    @RequestMapping(value="/email/sendEmail", method= RequestMethod.POST)
    Result sendEmail(@RequestBody @Valid SendEmailDTO sendEmail);
}
