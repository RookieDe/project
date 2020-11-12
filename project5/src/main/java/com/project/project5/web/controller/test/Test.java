package com.project.project5.web.controller.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.project.project5.config.InitConfig;
import com.project.project5.config.annotation.PassLogger;
import com.project.project5.util.MultipartFileToFile;
import com.project.project5.web.controller.base.BaseController;
import com.project.project5.dto.testDTO.request.EventRequest;
import com.project.project5.enums.ExceptionEnums;
import com.project.project5.service.impl.TestService;
import com.project.project5.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName test
 * @date 2020/4/2 11:02
 */
@RestController
@Api(value = "", description = "测试类相关接口（RookieDe）")
public class Test extends BaseController {

    @Resource(name = "testService")
    private TestService testService;

    @ApiOperation(value = "获取getMap信息", notes = "注意事项")
    @GetMapping(value = "/getMap/{key}")
    public Object test(@PathVariable("key") String key) {
        System.err.println(InitConfig.getMap().get(key));

        return ResultUtils.success("getMap/Test");
    }

    @ApiOperation(value = "获取getMap1四季信息", notes = "注意事项")
    @PassLogger
    @GetMapping(value = "/getMap1/{key}")
    public Object test1(@PathVariable("key") String key) {
        return ResultUtils.success("用户" + getUserInfo().getUserName() + "获取的数据为：" + testService.getMap1(key));
    }

    @ApiOperation(value = "Event时间监听测试")
    @PostMapping(value = "/eventTest")
    public Object eventTest(@RequestBody EventRequest eventRequest) {
        try {
            return ResultUtils.success(testService.eventTest(eventRequest));
        } catch (Exception e) {
            return ResultUtils.error(ExceptionEnums.FAILED.getCode(), e.getMessage());
        }
    }


    @ApiOperation(value = "导入测试")
    @PostMapping(value = "/importTest")
    public void importTest(@RequestParam("file") MultipartFile file) {

        try {
            File file1 = MultipartFileToFile.multipartFileToFile(file);
            ExcelReader reader = ExcelUtil.getReader(file1);
            List<List<Object>> read = reader.read();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
