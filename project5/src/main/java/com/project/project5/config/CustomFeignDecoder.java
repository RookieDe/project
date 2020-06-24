package com.project.project5.config;

import com.project.project5.service.BaseHeader;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * 自定义feign decoder处理器
 * @author RookieDe
 * @ClassName CustomFeignDecoder
 * @date 2020/6/23 18:16
 */
public class CustomFeignDecoder implements Decoder {

    private JacksonDecoder jacksonDecoder;

    public CustomFeignDecoder() {
        this.jacksonDecoder = new JacksonDecoder();
    }


    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        if (ObjectUtils.isEmpty(response)){
            return null;
        }
        Object decode = jacksonDecoder.decode(response, type);
        try {
            Class<?> aClass = Class.forName(type.getTypeName());
            Class<?>[] superClazz = aClass.getInterfaces();
            if (Arrays.asList(superClazz).contains(BaseHeader.class)){
                Map<String, Collection<String>> headers = response.headers();
                BaseHeader baseHeader = (BaseHeader)decode;
                baseHeader.setHeader(headers);
            }
            return decode;

        } catch (ClassNotFoundException e) {
            System.err.println("Feign decoder解析失败>>>>>>"+e.toString());
            e.printStackTrace();
        }
        System.out.println(Object.class);
        return decode;
    }
}
