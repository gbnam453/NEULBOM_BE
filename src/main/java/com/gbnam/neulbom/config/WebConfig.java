package com.gbnam.neulbom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // "/NoticeUploadImage/**" 경로로 접근하면 실제 파일 시스템의 "NoticeUploadImage/" 폴더에서 파일을 읽습니다.
        registry.addResourceHandler("/NoticeUploadImage/**")
                .addResourceLocations("file:NoticeUploadImage/");
    }
}