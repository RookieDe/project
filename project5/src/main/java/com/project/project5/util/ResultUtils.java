package com.project.project5.util;

import com.project.project5.entity.Response;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName ResultUtil
 * @date 2020/4/10 11:51
 */
public class ResultUtils {

    public static Response success(Integer code){
        return new Response(code);
    }

    public static Response success(Object object){
        return new Response(object);
    }

    public static Response error(Integer code,Object object){
        return new Response(code,object);
    }

}
