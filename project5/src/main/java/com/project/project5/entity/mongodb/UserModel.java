package com.project.project5.entity.mongodb;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName UserModel
 * @date 2020/4/14 11:34
 */
public class UserModel {

    private String adminId;

    private String name;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "adminId='" + adminId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
