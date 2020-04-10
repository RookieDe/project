package com.example.project1.common.security;

import com.alibaba.fastjson.JSON;
import com.example.project1.common.Enums.ResultEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户未登陆返回给前端的数据
 *
 * @Author RookieDe
 * @Date 2019/6/23 17:57
 * @Version 1.0
 */
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.getWriter().write(JSON.toJSONString(ResultEnum.USER_NEED_AUTHORITIES, false));
    }
}
