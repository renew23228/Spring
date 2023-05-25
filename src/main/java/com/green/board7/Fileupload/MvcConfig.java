package com.green.board7.Fileupload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("D:/download/") // D:/download/ 는 절대경로
    private String fileDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory(fileDir, registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName); // 상대경로를 절대경로를 바꿔주는 소스.
        String uploadPath = uploadDir.toFile().getAbsolutePath(); // 상대경로를 절대경로를 바꿔주는 소스
        if (dirName.startsWith("../")) dirName = dirName.replace("../", ""); // 상대경로를 절대경로를 바꿔주는 소스
        registry.addResourceHandler("/images/**").addResourceLocations("file:/"+ uploadPath + "/");
    }
}
