package com.project.project5.config.interceptor;

import com.project.project5.config.CommonConstant;
import com.project.project5.config.annotation.PassToken;
import com.project.project5.enums.ExceptionEnums;
import com.project.project5.exception.GlobalException;
import com.project.project5.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.handler.Handler;
import java.lang.reflect.Method;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName AuthenticationInterceptor
 * @date 2020/4/9 17:34
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tokens = request.getHeader(CommonConstant.HEADER_AUTHORIZATION);

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)){
            PassToken annotation = method.getAnnotation(PassToken.class);
            if (annotation.required()){
                return true;
            }
        }

        //验证token是否为空
        if (StringUtils.isEmpty(tokens) || !tokens.startsWith(CommonConstant.BEARER_AUTHENTICATION)){
            throw new GlobalException(ExceptionEnums.LOGINOUT);
        }

        // 取出token
        String token = tokens.split(" ")[1];

        //校验token
        if (JwtUtils.isExpiration(token)){
            throw new GlobalException(ExceptionEnums.LOGINOUT);
        }

        //取出token数据
        Claims claims = JwtUtils.checkJWT(token);
        if (claims != null){
            claims.get("username");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
