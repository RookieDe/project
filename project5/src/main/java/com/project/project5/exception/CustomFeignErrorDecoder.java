package com.project.project5.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * description:自定义feign错误拦截器
 * @author RookieDe
 * @ClassName CustomFeignErrorDecoder
 * @date 2020/6/24 9:55
 */
public class CustomFeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        if(response == null) {
            System.err.println("第三方服务调用异常>>>>>>>"+ "方法：" + s);
        }else {
            System.err.println("第三方服务调用异常>>>>>>>"+"方法：" + s + "status：" + response.status() + " response：" + response.body().toString());
        }
        return new RuntimeException("第三方服务调用异常");
    }
}
