package com.springboot.config.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/** Spring 静态资源配置
*
* @version 创建时间：2018年3月12日 下午13:52:00
* 
*/
@Configuration
public class WebAppConfigure extends WebMvcConfigurerAdapter {

    // app主页图片物理路径
    @Value("${app_home_picture_file_path}")
    private String housePictureUrl;

    @Value("${vus.static.resources}")
    private String serverFilePath;
    
    /**
    * Title: addResourceHandlers 
    * Description:spring静态资源文件处理
    * @param registry 
    * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
    */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        // app首页图片资源
        registry.addResourceHandler("/resources/app/home/picture/**").addResourceLocations(
            "file:" + housePictureUrl);

        // app首页图片资源
        registry.addResourceHandler("/resources/logo/**").addResourceLocations(
                "file:" + serverFilePath);
    }

}
