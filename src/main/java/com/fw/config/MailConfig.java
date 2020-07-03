package com.fw.config;

import com.fw.domain.SysMail;
import com.fw.mapper.SysMailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author yqf
 */
@Configuration
public class MailConfig {

    @Autowired
    private SysMailMapper sysMailMapper;

    @Bean
    JavaMailSenderImpl mailSender() throws MessagingException {
        SysMail sysMail = sysMailMapper.findOne();
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setDefaultEncoding("UTF-8");

        Properties properties = new Properties();
        //properties.setProperty("mail.debug", "true");//启用调试
        //properties.setProperty("mail.smtp.timeout", "1000");//设置链接超时
        //设置通过ssl协议使用465端口发送、使用默认端口（25）时下面三行不需要
        //开启认证
        properties.setProperty("mail.smtp.auth", "true");
        //设置ssl端口
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


        javaMailSender.setHost(sysMail.getHost());
        javaMailSender.setProtocol(sysMail.getProtocol());
        javaMailSender.setUsername(sysMail.getUserName());
        javaMailSender.setPassword(sysMail.getPassword());

        javaMailSender.setJavaMailProperties(properties);

        return javaMailSender;
    }


}
