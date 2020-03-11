package com.cloud.message.service.impl;

import com.cloud.message.service.SendEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


/**
 * <pre>
 * 对象功能:邮件发送实现类
 * 开发人员:shiyd
 * 创建时间:2018-11-12
 * </pre>
 */
@Service
@Slf4j
public class SendEmailServiceImpl implements SendEmailService {

    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String fromAddress;

    @Override
    public boolean sendTextMail(String subject, String content, String toAddress) {
        boolean bool = true;
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromAddress);
            message.setTo(toAddress);
            message.setSubject(subject);
            message.setText(content);
            sender.send(message);
        } catch (Exception e) {
            bool = false;
            log.error("sendTextMail发送邮件失败!", e);
        }
        return bool;
    }

    @Override
    public boolean sendTextMail(String subject, String content, String toAddress, String... toCCAddress) {
        boolean bool = true;
        try {
        
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromAddress);
            message.setTo(toAddress);
            message.setCc(toCCAddress);
            message.setSubject(subject);
            message.setText(content);
            sender.send(message);
        } catch (Exception e) {
            bool = false;
            log.error("sendTextMail发送邮件失败!", e);
        }
        return bool;
    }

    @Override
    public boolean sendHtmlMail(String subject, String contentHtml, String toAddress) {
        boolean bool = true;
        try {
            MimeMessage message = sender.createMimeMessage();
            // true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromAddress);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(contentHtml, true);
            sender.send(message);
        } catch (MessagingException e) {
            bool = false;
            log.error("sendHtmlMail发送邮件失败!", e);
        }
        return bool;
    }

    @Override
    public boolean sendHtmlMail(String subject, String contentHtml, String toAddress, String... toCCAddress) {
        boolean bool = true;
        try {
            MimeMessage message = sender.createMimeMessage();
            // true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromAddress);
            helper.setTo(toAddress);
            helper.setCc(toCCAddress);
            helper.setSubject(subject);
            helper.setText(contentHtml, true);
            sender.send(message);
        } catch (MessagingException e) {
            bool = false;
            log.error("sendHtmlMail发送邮件失败!", e);
        }
        return bool;
    }

    @Override
    public boolean sendAttachmentsMail(String subject, String content, String filePath, String toAddress) {
        boolean bool = true;
        try {
            MimeMessage message = sender.createMimeMessage();
            // true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromAddress);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);

            sender.send(message);
        } catch (MessagingException e) {
            bool = false;
            log.error("sendAttachmentsMail发送添加附件邮件失败!", e);
        }
        return bool;
    }

    @Override
    public boolean sendAttachmentsMail(String subject, String content, String filePath, String toAddress,
                                       String... toCCAddress) {
        boolean bool = true;
        try {
            MimeMessage message = sender.createMimeMessage();
            // true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromAddress);
            helper.setTo(toAddress);
            helper.setCc(toCCAddress);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);

            sender.send(message);
        } catch (MessagingException e) {
            bool = false;
            log.error("sendAttachmentsMail发送邮件失败!", e);
        }
        return bool;
    }

    @Override
    public boolean sendInlineResourceMail(String subject, String content, String rscPath, String rscId,
                                          String toAddress) {
        boolean bool = true;
        try {
            MimeMessage message = sender.createMimeMessage();
            // true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromAddress);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);
            sender.send(message);
        } catch (MessagingException e) {
            bool = false;
            log.error("发送嵌入静态资源（一般是图片）的邮件失败!", e);
        }
        return bool;
    }

    @Override
    public boolean sendInlineResourceMail(String subject, String content, String rscPath, String rscId,
                                          String toAddress, String... toCCAddress) {
        boolean bool = true;
        try {
            MimeMessage message = sender.createMimeMessage();
            // true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromAddress);
            helper.setTo(toAddress);
            helper.setCc(toCCAddress);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);
            sender.send(message);
        } catch (MessagingException e) {
            bool = false;
            log.error("发送嵌入静态资源（一般是图片）的邮件失败!", e);
        }
        return bool;
    }

}
