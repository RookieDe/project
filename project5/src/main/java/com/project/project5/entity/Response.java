package com.project.project5.entity;

import com.project.project5.enums.ExceptionEnums;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName Result
 * @date 2020/4/10 11:31
 */
public class Response<T> {

    //返回码
    private Integer code;

    //返回提示信息
    private String msg;

    //返回实体
    private T date;

    public Response(Integer code){
        this.code = code;
        this.msg = ExceptionEnums.getMsgByCode(code);
    }

    public Response(Integer code,T date){
        this.code = code;
        this.msg = ExceptionEnums.getMsgByCode(code);
        this.date = date;
    }

    public Response(T date){
        this.code = ExceptionEnums.SUCCESS.getCode();
        this.msg = ExceptionEnums.SUCCESS.getMsg();
        this.date = date;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }


}
