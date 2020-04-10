package com.project.project5.controller.login;

import com.project.project5.entity.User;
import com.project.project5.enums.ExceptionEnums;
import com.project.project5.util.JwtUtils;
import com.project.project5.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
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
    @ApiOperation(value = "获取token接口",notes = "注意事项")
    @PostMapping("getToken")
    public Object getToken(@Valid @RequestBody User user){
        return ResultUtils.success(JwtUtils.generateJsonWebToken(user));
    }

    @ApiOperation(value = "获取token过期时间")
    @GetMapping("getRemainingTokenTime")
    public Object getRemainingTokenTime(@RequestParam("token") String token){
        try {
            return ResultUtils.success(JwtUtils.getRemainingTokenTime(token));
        }catch (Exception e){
            return ResultUtils.error(ExceptionEnums.FAILED.getCode(),e.getMessage());
        }
    }
}
