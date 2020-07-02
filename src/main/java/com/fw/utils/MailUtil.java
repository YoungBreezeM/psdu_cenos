package com.fw.utils;

import com.fw.domain.SysMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author yqf
 */
@Component
public class MailUtil {

    @Autowired
    private JavaMailSenderImpl mailSender;

    public void send(String form,String to,String subject,String text) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true,"utf-8");
        mimeMessageHelper.setFrom(form);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text,true);
        ClassPathResource logoImage = new ClassPathResource("static/bg.jpg");
        mimeMessageHelper.addInline("bg",logoImage);
        mailSender.send(mimeMessage);
    }
}
