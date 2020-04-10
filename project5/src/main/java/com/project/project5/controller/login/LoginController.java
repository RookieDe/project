package com.project.project5.controller.login;

import com.project.project5.entity.User;
import com.project.project5.enums.ExceptionEnums;
import com.project.project5.util.JwtUtils;
import com.project.project5.util.ResultUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName LoginController
 * @date 2020/4/10 12:42
 */
@RestController
@RequestMapping(value = "login", produces = "application/json;charset=utf-8")
public class LoginController {

    /**
     * 获取token
     * @return
     */
    @PostMapping("getToken")
    public Object getToken(User user){
        return ResultUtils.success(JwtUtils.generateJsonWebToken(user));
    }


    @GetMapping("getRemainingTokenTime")
    public Object getRemainingTokenTime(@RequestParam("token") String token){
        try {
            return ResultUtils.success(JwtUtils.getRemainingTokenTime(token));
        }catch (Exception e){
            return ResultUtils.error(ExceptionEnums.FAILED.getCode(),e.getMessage());
        }
    }
}
