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
        javaMailSender.setHost(sysMail.getHost());
        javaMailSender.setProtocol(sysMail.getProtocol());
        javaMailSender.setUsername(sysMail.getUserName());
        javaMailSender.setPassword(sysMail.getPassword());

        return javaMailSender;
    }


}
