package com.example.project1.common.Enums;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * 返回错误码枚举
 *
 * @Author RookieDe
 * @Date 2019/6/20 0:17
 * @Version 1.0
 */
public enum ResultEnum {

    //返回码
    SUCCESS(200, "成功！"),
    FAILURE(201, "失败！"),
    USER_NEED_AUTHORITIES(201, "用户未登录"),
    USER_LOGIN_FAILED(202, "用户账号或密码错误"),
    USER_LOGIN_SUCCESS(203, "用户登录成功"),
    USER_NO_ACCESS(204, "用户无权访问"),
    USER_LOGOUT_SUCCESS(205, "用户登出成功"),
    TOKEN_IS_BLACKLIST(206, "此token为黑名单"),
    LOGIN_IS_OVERDUE(207, "登录已失效");


    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过code返回枚举
     *
     * @param code
     * @return ResultEnum
     * @author RookieDe
     * @Date 2019/6/20 21:19
     */
    public static ResultEnum parse(int code) {
        ResultEnum[] values = values();
        for (ResultEnum value : values) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new RuntimeException("Unknown code of ResultEnum");
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
