package com.project.project5.service;

import com.project.project5.config.InitConfig;
import org.springframework.stereotype.Service;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName testService
 * @date 2020/4/2 11:33
 */
@Service("testService")
public class TestService {

    public String getMap1(String key){
        return InitConfig.getMap().get(key).toString();
    }
}
