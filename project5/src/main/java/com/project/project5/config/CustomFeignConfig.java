package com.project.project5.config;

import com.project.project5.config.interceptor.CustomFeignInterceptor;
import com.project.project5.exception.CustomFeignErrorDecoder;
import com.project.project5.properties.ProjectCoreProperties;
import com.project.project5.service.ProjectPermissionFeign;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * description: 自定义feign
 * @author RookieDe
 * @ClassName CustomFeignConfig
 * @date 2020/6/23 18:29
 *
 */
@Configuration
public class CustomFeignConfig {

    @Autowired
    private ProjectCoreProperties projectCoreProperties;

    @Bean
    public ProjectPermissionFeign createCustomFeign() {
        return Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new JacksonEncoder())
                .decoder(new CustomFeignDecoder())
                .logger(new Slf4jLogger("com.project.project5.service.ProjectPermissionFeign"))
                .logLevel(Logger.Level.FULL)
                .requestInterceptor(new CustomFeignInterceptor(projectCoreProperties))
                .errorDecoder(new CustomFeignErrorDecoder())
                .target(ProjectPermissionFeign.class, projectCoreProperties.getDocking().getHost());
    }
}
