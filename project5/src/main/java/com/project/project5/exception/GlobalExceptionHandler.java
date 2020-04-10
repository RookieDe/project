package com.project.project5.exception;

import com.project.project5.entity.Response;
import com.project.project5.enums.ExceptionEnums;
import com.project.project5.util.ResultUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName GlobalExceptionHandler
 * @date 2020/4/10 11:16
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public Object handleException(Exception e){
        if (e instanceof GlobalException){
            GlobalException globalException = (GlobalException)e;
            return ResultUtils.error(globalException.getEnums().getCode(),e.getMessage());
        }else {
            return ResultUtils.error(ExceptionEnums.SERVER_ERROR.getCode(),e.getMessage());
        }
    }
}
