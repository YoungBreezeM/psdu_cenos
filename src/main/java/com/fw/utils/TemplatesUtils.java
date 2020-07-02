package com.fw.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * @author yqf
 */
@Component
public class TemplatesUtils {

    public String renderCaptcha(Integer captcha){
        TemplateEngine templateEngine = new TemplateEngine();

        Context context = new Context();

        context.setVariable("captcha",captcha);

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/mail/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(0);

        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process("/mail", context);

    }
}
