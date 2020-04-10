package com.project.project5.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Shanghai *** Technology Co.,Ltd.
 *  初始化字典
 * @author RookieDe
 * @ClassName InitConfig
 * @date 2020/4/2 10:27
 */
@Configuration
public class InitConfig {

    private static Map<String,Object> map = new HashMap<>(16);

    @PostConstruct
    public void setMap(){
        map.put("spring","春季");
        map.put("summer","夏季");
        map.put("autumn","秋季");
        map.put("winter","冬季");
    }


    public static Map<String,Object> getMap(){
        return map;
    }
}
