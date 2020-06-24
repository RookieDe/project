package com.sj56.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName RouteConfiguration
 * @date 2019/11/7 16:39
 */
@Configuration
public class RouteConfiguration {

    @Bean
    public RouteLocator addRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("permission",
                        r -> r.path("/sms/**")
                                .uri("http://121.199.199.4:80"))
                .route("auth",
                        r -> r.path("/auth/**")
                                .uri("http://121.199.199.4:80"))
                .route("app",
                        r -> r.path("/app/**")
                                .uri("http://localhost:9040"))
                .route("app",
                        r -> r.path("/common/**")
                                .uri("http://localhost:9040"))
                .build();
    }
}
