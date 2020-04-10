package com.project.project5.controller;

import com.project.project5.config.InitConfig;
import com.project.project5.config.annotation.PassLogger;
import com.project.project5.controller.base.BaseController;
import com.project.project5.entity.Response;
import com.project.project5.service.TestService;
import com.project.project5.util.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName test
 * @date 2020/4/2 11:02
 */
@RestController
public class Test extends BaseController {

    @Resource(name = "testService")
    private TestService testService;


    @GetMapping(value = "/getMap/{key}")
    public Object test(@PathVariable("key") String key){
        System.err.println(InitConfig.getMap().get(key));

        return ResultUtils.success("getMap/Test");
    }


    @PassLogger
    @GetMapping(value = "/getMap1/{key}")
    public Object test1(@PathVariable("key") String key){
        return ResultUtils.success("用户"+getUserInfo().getUserName()+"获取的数据为："+testService.getMap1(key));
    }
}
