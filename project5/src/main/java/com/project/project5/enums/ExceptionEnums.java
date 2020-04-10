package com.project.project5.enums;

public enum ExceptionEnums {

    /**
     * 错误码
     */
    SUCCESS(200,"操作成功"),
    FAILED(400,"操作失败"),
    SERVER_ERROR(500,"服务器错误"),

    LOGINOUT(1001,"token失效，请重新登陆！");

    private Integer code;
    private String msg;

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

    private ExceptionEnums(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据code获取提示信息
     * @param code
     * @return
     */
    public static String getMsgByCode(Integer code){
        for (ExceptionEnums enums : ExceptionEnums.values()) {
            if (enums.getCode().equals(code)){
                return enums.getMsg();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ExceptionEnums{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
