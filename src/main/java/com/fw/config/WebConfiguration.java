package com.fw.config;


import com.fw.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.*;

import java.util.concurrent.Executors;

/**
 * @author yqf
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private TokenInterceptor tokenInterceptor;



    /**
     * 解决跨域请求
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowCredentials(true);
    }

    /**
     * 异步请求配置
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
        configurer.setDefaultTimeout(30000);
    }

    /**
     * 配置拦截器、拦截路径
     * 每次请求到拦截的路径，就会去执行拦截器中的方法
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login/**")
                .excludePathPatterns("/register/**")
                .excludePathPatterns("/static/**");

    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}

