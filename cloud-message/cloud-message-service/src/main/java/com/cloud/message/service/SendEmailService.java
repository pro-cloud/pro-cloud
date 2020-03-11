package com.cloud.message.service;

/**
 * <pre>
 * 对象功能:邮件收发service接口类
 * 开发人员:shiyd
 * 创建时间:2018-11-12
 * </pre>
 */
public interface SendEmailService {

    /**
     * 发送纯文本的简单邮件
     *
     * @param subject:主题
     * @param content:内容
     * @param toAddress:邮件接收地址
     */
    public boolean sendTextMail(String subject, String content, String toAddress);

    /**
     * 发送纯文本的简单邮件
     *
     * @param subject:主题
     * @param content:内容
     * @param toAddress:邮件接收地址
     * @param toCCAddress:抄送邮件接收地址(可为数组)
     */
    public boolean sendTextMail(String subject, String content, String toAddress, String... toCCAddress);

    /**
     * 发送html格式的邮件
     *
     * @param subject:主题
     * @param contentHtml:html内容
     * @param toAddress:邮件接收地址
     */
    public boolean sendHtmlMail(String subject, String contentHtml, String toAddress);

    /**
     * 发送html格式的邮件
     *
     * @param subject:主题
     * @param contentHtml:html内容
     * @param toAddress:邮件接收地址
     * @param toCCAddress:抄送邮件接收地址(可为数组)
     */
    public boolean sendHtmlMail(String subject, String contentHtml, String toAddress, String... toCCAddress);

    /**
     * 发送带附件的邮件
     *
     * @param subject:主题
     * @param contentHtml:html内容
     * @param filePath:附件地址
     * @param toAddress:邮件接收地址
     */
    public boolean sendAttachmentsMail(String subject, String content, String filePath, String toAddress);

    /**
     * 发送带附件的邮件
     *
     * @param subject:主题
     * @param contentHtml:html内容
     * @param filePath:附件地址
     * @param toAddress:邮件接收地址
     */
    public boolean sendAttachmentsMail(String subject, String content, String filePath, String toAddress,
                                       String... toCCAddress);

    /**
     * 发送嵌入静态资源（一般是图片）的邮件
     *
     * @param subject:主题
     * @param content
     *            邮件内容，需要包括一个静态资源的id，比如：<img src=\"cid:rscId01\" >
     * @param rscPath
     *            静态资源路径和文件名
     * @param rscId
     *            静态资源id
     * @param toAddress:邮件接收地址
     */
    public boolean sendInlineResourceMail(String subject, String content, String rscPath, String rscId,
                                          String toAddress);

    /**
     * 发送嵌入静态资源（一般是图片）的邮件
     *
     * @param subject:主题
     * @param content
     *            邮件内容，需要包括一个静态资源的id，比如：<img src=\"cid:rscId01\" >
     * @param rscPath
     *            静态资源路径和文件名
     * @param rscId
     *            静态资源id
     * @param toAddress:邮件接收地址
     * @param toCCAddress:抄送邮件接收地址(可为数组)
     */
    public boolean sendInlineResourceMail(String subject, String content, String rscPath, String rscId,
                                          String toAddress, String... toCCAddress);

}
