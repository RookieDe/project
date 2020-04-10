package com.example.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName TestController
 * @date 2019/9/16 9:38
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/getDate")
    public String getDate() {
        return "测试成功";
    }


    @ExceptionHandler
    public String doError(Exception ex) throws Exception {
        ex.printStackTrace();
        return ex.getMessage();
    }

}
