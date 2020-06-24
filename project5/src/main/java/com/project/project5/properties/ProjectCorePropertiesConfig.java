package com.project.project5.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * 系统核心配置
 * @author RookieDe
 * @ClassName ProjectCorePropertiesConfig
 * @date 2020/6/23 18:30
 */
@Configuration
@EnableConfigurationProperties(ProjectCoreProperties.class)
public class ProjectCorePropertiesConfig {

}
