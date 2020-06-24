package com.project.project5.config.interceptor;

import com.project.project5.config.UserContext;
import com.project.project5.properties.ProjectCoreProperties;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.util.StringUtils;

import static com.project.project5.config.CommonConstant.BEARER_AUTHENTICATION;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * description:自定义feign拦截器
 * @author RookieDe
 * @ClassName CustomFeignInterceptor
 * @date 2020/6/23 18:47
 */
public class CustomFeignInterceptor implements RequestInterceptor {

    private ProjectCoreProperties coreProperties;

    public CustomFeignInterceptor(ProjectCoreProperties coreProperties) {
        this.coreProperties = coreProperties;
    }


    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate
                .header("platform", coreProperties.getDocking().getPlatform())
                .header("projectName", coreProperties.getDocking().getProjectName())
                .header("appcode", coreProperties.getDocking().getAppcode())
                .header("appkey", coreProperties.getDocking().getAppkey());
        String token = UserContext.getToken();
        if (!StringUtils.isEmpty(token)) {
            requestTemplate.header("Authorization", BEARER_AUTHENTICATION + token);
        }
    }
}
