package com.project.project5.controller.base;

import com.project.project5.config.CommonConstant;
import com.project.project5.dto.authDTO.response.AuthInfoResponse;
import com.project.project5.enums.ExceptionEnums;
import com.project.project5.exception.GlobalException;
import com.project.project5.util.JwtUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName BaseController
 * @date 2020/4/10 13:13
 */
public class BaseController {

    public HttpServletRequest getRequest(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        return servletRequestAttributes.getRequest();
    }

    public AuthInfoResponse getUser(HttpServletRequest httpServletRequest){
        String tokens = httpServletRequest.getHeader(CommonConstant.HEADER_AUTHORIZATION);
        //验证token是否为空
        if (StringUtils.isEmpty(tokens) || !tokens.startsWith(CommonConstant.BEARER_AUTHENTICATION)){
            throw new GlobalException(ExceptionEnums.LOGINOUT);
        }

        // 取出token
        String token = tokens.split(" ")[1];
        return JwtUtils.getUserInfo(token);
    }

    public AuthInfoResponse getUserInfo(){
        HttpServletRequest httpServletRequest = getRequest();
        return getUser(httpServletRequest);
    }
}
