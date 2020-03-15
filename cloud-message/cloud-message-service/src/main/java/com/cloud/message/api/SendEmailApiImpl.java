package com.cloud.message.api;


import cn.hutool.core.util.StrUtil;
import com.cloud.common.data.base.Result;
import com.cloud.common.data.enums.ResultEnum;
import com.cloud.common.data.exception.BaseException;

import com.cloud.message.beans.dto.SendEmailDTO;
import com.cloud.message.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @Author Aijm
 * @Description 发送邮件Controller类
 * @Date 2019/9/11
 */
@RestController
public class SendEmailApiImpl implements ISendEmailService {

    @Autowired
    private SendEmailService sendEmailService;

    @Override
    public Result sendEmail(@RequestBody @Valid SendEmailDTO sendEmail) {

        if (SendEmailDTO.TEXTMAIL.equals(sendEmail.getEmailType())) {

            sendEmailService.sendTextMail(sendEmail.getSubject(), sendEmail.getContent(),
                    sendEmail.getToAddress());

        } else if (SendEmailDTO.TEXTMAIL_CC.equals(sendEmail.getEmailType())) {

            if (StrUtil.isBlank(sendEmail.getToCCAddress())) {
                throw new BaseException(ResultEnum.PARAM_ADDR);
            }
            String[] split = sendEmail.getToCCAddress().split(",");
            sendEmailService.sendTextMail(sendEmail.getSubject(), sendEmail.getContent(),
                    sendEmail.getToAddress(), split);

        } else if (SendEmailDTO.HTMLMAIL.equals(sendEmail.getEmailType())) {

            sendEmailService.sendHtmlMail(sendEmail.getSubject(), sendEmail.getContent(),
                    sendEmail.getToAddress());

        } else if (SendEmailDTO.HTMLMAIL_CC.equals(sendEmail.getEmailType())) {

            if (StrUtil.isBlank(sendEmail.getToCCAddress())) {
                throw new BaseException(ResultEnum.PARAM_ADDR);
            }
            sendEmailService.sendHtmlMail(sendEmail.getSubject(), sendEmail.getContent(),
                    sendEmail.getToAddress(), sendEmail.getToCCAddress());

        } else if (SendEmailDTO.ATTACHMENTSMAIL.equals(sendEmail.getEmailType())) {

            if (StrUtil.isBlank(sendEmail.getFilePath())) {
                throw new BaseException(ResultEnum.PARAM_ADDR_ATTACH);
            }
            sendEmailService.sendAttachmentsMail(sendEmail.getSubject(), sendEmail.getContent(),
                    sendEmail.getFilePath(), sendEmail.getToAddress());

        } else if (SendEmailDTO.ATTACHMENTSMAIL_CC.equals(sendEmail.getEmailType())) {

            if (StrUtil.isBlank(sendEmail.getToCCAddress())) {
                throw new BaseException(ResultEnum.PARAM_ADDR);
            }
            if (StrUtil.isBlank(sendEmail.getFilePath())) {
                throw new BaseException(ResultEnum.PARAM_ADDR_ATTACH);
            }
            sendEmailService.sendAttachmentsMail(sendEmail.getSubject(), sendEmail.getContent(),
                    sendEmail.getFilePath(), sendEmail.getToAddress(), sendEmail.getToCCAddress());

        } else if (SendEmailDTO.INLINERESOURCEMAIL.equals(sendEmail.getEmailType())) {

            sendEmailService.sendInlineResourceMail(sendEmail.getSubject(), sendEmail.getContent(),
                    sendEmail.getRscPath(), sendEmail.getRscId(), sendEmail.getToAddress());

        } else if (SendEmailDTO.INLINERESOURCEMAIL_CC.equals(sendEmail.getEmailType())) {

            if (StrUtil.isBlank(sendEmail.getToCCAddress())) {
                throw new BaseException(ResultEnum.PARAM_ADDR);
            }

            if (StrUtil.isBlank(sendEmail.getRscPath())) {
                throw new BaseException(ResultEnum.PARAM_ADDR_ASC);
            }

            if (StrUtil.isBlank(sendEmail.getRscId())) {
                throw new BaseException("参数邮件静态资源id{sendEmail.getRscId()}为空,无法发送邮件!");
            }
            sendEmailService.sendInlineResourceMail(sendEmail.getSubject(), sendEmail.getContent(),
                    sendEmail.getRscPath(), sendEmail.getRscId(), sendEmail.getToAddress(), sendEmail.getToCCAddress());
        }
        return Result.success("");
    }
}
