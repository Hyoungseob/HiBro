package com.HiBro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${uploadPath}")
    String uploadPath;
    @Value("${imgUploadPath}")
    String imgUploadPath;
    @Value("${videoUploadPath}")
    String videoUploadPath;



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/CinemaImg/**")
                .addResourceLocations(uploadPath + imgUploadPath);
        registry.addResourceHandler("/CinemaVideo/**")
                .addResourceLocations(uploadPath + videoUploadPath);
    }
}
