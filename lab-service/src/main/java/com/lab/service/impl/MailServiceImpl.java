package com.lab.service.impl;

import com.lab.service.MailService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;

    public MailServiceImpl (JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /*
     * 发送只包含文本内容的邮件
     * 指定发送者的邮箱地址，接收者的邮箱地址，主题和内容
     * */
    @Override
    public void sendSimpleMail (String from, String[] to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    /*
     * 发送只包含文本内容的邮件，对不同的收件人内容也不同
     * 指定发送者的邮箱地址，接收者的邮箱地址，主题和内容
     * */
    @Override
    public void sendSimpleMail (String from, String[] to, String subject, List<String> contents) {
        for (int i = 0; i < to.length; i++) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to[i]);
            message.setSubject(subject);
            message.setText(contents.get(i));
            mailSender.send(message);
        }
    }

    /*
     * 发送HTML格式的邮件
     * */
    @Override
    public void sendHtmlMail (String from, String[] to, String subject, String content) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true); // 设置 HTML 格式
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /*
     * 发送带附件的、HTML 格式的邮件
     * 需要额外指定 文件的绝对路径/url 和 文件名.后缀
     * */
    @Override
    public void sendHtmlMailWithFile (String from, String[] to, String subject, String content, String filePath, String fileName) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            // 添加附件
            FileSystemResource file = new FileSystemResource(new File(filePath));
            helper.addAttachment(fileName, file);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /*
     * 发送带多个附件的、HTML 格式的邮件
     * */
    @Override
    public void sendHtmlMailWithFiles (String from, String[] to, String subject, String content, List<String> filePaths, List<String> fileNames) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            // 添加多个附件
            for (int i = 0; i < filePaths.size(); i++) {
                FileSystemResource file = new FileSystemResource(new File(filePaths.get(i)));
                helper.addAttachment(fileNames.get(i), file);
            }

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}