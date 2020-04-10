package com.project.project5.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName User
 * @date 2020/4/9 9:59
 */
public class User {

    @ApiModelProperty("用户id")
    @NotNull(message = "userId不能为空")
    private Integer userId;

    @ApiModelProperty("用户名称")
    @NotEmpty(message = "userName不能为空")
    private String userName;

    @ApiModelProperty("用户密码")
    @NotEmpty(message = "password不能为空")
    private String password;

    @ApiModelProperty("角色")
    private String role;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
