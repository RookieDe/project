package com.project.project5.service.impl;

import com.project.project5.config.InitConfig;
import com.project.project5.config.listener.event.TestEvent;
import com.project.project5.dto.testDTO.request.EventRequest;
import com.project.project5.service.ProjectPermissionFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

    @Autowired
    private ProjectPermissionFeign feign;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public String getMap1(String key){
        return InitConfig.getMap().get(key).toString();
    }


    public Object eventTest(EventRequest eventRequest) {
        //插入数据

        //监听事件
        applicationEventPublisher.publishEvent(new TestEvent("eventListener","这个是要执行的业务数据参数"));
        return 1;
    }
}
