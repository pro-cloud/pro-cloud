package com.cloud.message.beans.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author Aijm
 * @Description 邮件实体类
 * @Date 2019/9/11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 邮件类型
     */
    @NotBlank(message = " 主题不能为空")
    private String emailType;

    /**
     * 主题
     */
    @NotBlank(message = " 主题不能为空")
    private String subject;

    /**
     * 内容
     */
    @NotBlank(message = " 内容不能为空")
    private String content;

    /**
     * 邮件接收地址
     */
    @NotBlank(message = " 邮件接收地址不能为空")
    private String toAddress;

    /**
     * 邮件抄送地址
     */
    private String toCCAddress;

    /**
     * 附件地址
     */
    private String filePath;

    /**
     * 静态资源路径和文件名
     */
    private String rscPath;

    /**
     * 静态资源id
     */
    private String rscId;


    /**
     * 发送text格式的邮件标志
     **/
    public static final String TEXTMAIL = "textMail";

    /**
     * 发送html格式的邮件标志（带抄送人）
     **/
    public static final String TEXTMAIL_CC = "textMail_Cc";

    /**
     * 发送纯文本的简单邮件标志
     **/
    public static final String HTMLMAIL = "htmlMail";

    /**
     * 发送纯文本的简单邮件标志（带抄送人）
     **/
    public static final String HTMLMAIL_CC = "htmlMail_Cc";

    /**
     * 发送带附件的邮件标志
     **/
    public static final String ATTACHMENTSMAIL = "attachmentsMail";

    /**
     * 发送带附件的邮件标志（带抄送人）
     **/
    public static final String ATTACHMENTSMAIL_CC = "attachmentsMail_Cc";

    /**
     * 发送嵌入静态资源（一般是图片）的邮件标志
     **/
    public static final String INLINERESOURCEMAIL = "inlineResourceMail";

    /**
     * 发送嵌入静态资源（一般是图片）的邮件标志（带抄送人）
     **/
    public static final String INLINERESOURCEMAIL_CC = "inlineResourceMail_Cc";
}
