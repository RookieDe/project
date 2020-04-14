package com.project.project5.entity.mongodb;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.project.project5.controller.base.BaseController;
import com.sun.corba.se.spi.ior.ObjectId;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName BaseModel
 * @date 2020/4/14 11:33
 */
public class BaseModel {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private String _id;

    @CreatedBy
    private UserModel userInfo;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date modifiedDate;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public UserModel getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserModel userInfo) {
        this.userInfo = userInfo;
    }
}
