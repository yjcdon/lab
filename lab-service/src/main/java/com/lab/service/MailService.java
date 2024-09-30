package com.lab.service;

import java.util.List;

public interface MailService {
    /**
     * 发送只包含文本内容的邮件
     *
     * @param from    发送者的邮箱地址
     * @param to      接收者的邮箱地址数组
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    void sendSimpleMail (String from, String[] to, String subject, String content);

     /**
     * 发送只包含文本内容的邮件，不同的收件人内容也不同
     *
     * @param from    发送者的邮箱地址
     * @param to      接收者的邮箱地址数组
     * @param subject 邮件主题
     * @param contents 邮件内容
     */
    void sendSimpleMail (String from, String[] to, String subject, List<String> contents);

    /**
     * 发送 HTML 格式的邮件
     *
     * @param from    发送者的邮箱地址
     * @param to      接收者的邮箱地址数组
     * @param subject 邮件主题
     * @param content HTML 格式的邮件内容
     */
    void sendHtmlMail (String from, String[] to, String subject, String content);

    /**
     * 发送带附件的、HTML 格式的邮件
     *
     * @param from     发送者的邮箱地址
     * @param to       接收者的邮箱地址数组
     * @param subject  邮件主题
     * @param content  HTML 格式的邮件内容
     * @param filePath 附件的绝对路径
     * @param fileName 附件的文件名
     */
    void sendHtmlMailWithFile (String from, String[] to, String subject, String content, String filePath, String fileName);

    /**
     * 发送带多个附件的、HTML 格式的邮件
     *
     * @param from      发送者的邮箱地址
     * @param to        接收者的邮箱地址数组
     * @param subject   邮件主题
     * @param content   HTML 格式的邮件内容
     * @param filePaths 附件的绝对路径列表
     * @param fileNames 附件的文件名列表
     */
    void sendHtmlMailWithFiles (String from, String[] to, String subject, String content, List<String> filePaths, List<String> fileNames);
}