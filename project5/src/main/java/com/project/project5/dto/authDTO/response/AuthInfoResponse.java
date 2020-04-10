package com.project.project5.dto.authDTO.response;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName AuthInfoResponse
 * @date 2020/4/10 13:21
 */
public class AuthInfoResponse {

    private Integer userId;

    private String userName;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AuthInfoResponse{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
