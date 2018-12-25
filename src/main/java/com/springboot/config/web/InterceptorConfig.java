package com.springboot.config.web;


import com.springboot.util.ApplicationContextHolder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *  on 2017/09/24.
 */
@Lazy
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WatchInterceptor watchInterceptor = new WatchInterceptor();
        ApplicationContextHolder.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(watchInterceptor);
        registry.addInterceptor(watchInterceptor).addPathPatterns("/**");
    }

}
