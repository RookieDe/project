package com.example.project1.common.VO;

import com.example.project1.common.Enums.ResultEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author RookieDe
 * @Date 2019/6/20 23:04
 * @Version 1.0
 */
public final class ResultVO implements Serializable {

    private static final long serialVersionUID = 1725159680599612404L;

    /**
     * 返回msg，object，以及token
     * 返回的code为默认
     * @Date 2019/6/20 23:10
     * @return map
     */
    public final static Map<String, Object> success(String message,Object data,String jwtToken,Boolean success){
        Map<String, Object> map = new HashMap<>(6);
        map.put("jwtToken",jwtToken);
        map.put("code", ResultEnum.SUCCESS.getCode());
        map.put("message",message);
        map.put("success",success);
        map.put("data",data);
        return map;
    }

    /**
     * 返回Object，token
     * 返回的msg，code为默认
     * @Date 2019/6/20 23:19
     * @param data
     * @return map
     */
    public final static Map<String,Object> success(Object data,String jwtToken){
        Map<String, Object> map = new HashMap<>(6);
        map.put("jwtToken",jwtToken);
        map.put("code", ResultEnum.SUCCESS.getCode());
        map.put("message", ResultEnum.SUCCESS.getMessage());
        map.put("success",true);
        map.put("data",data);
        return map;
    }

    /**
     * 返回默认信息
     * @Date 2019/6/20 23:23
     * @return map
     */
    public final static Map<String,Object> success(){
        Map<String,Object> map = new HashMap<>(6);
        map.put("jwtToken",null);
        map.put("code", ResultEnum.SUCCESS.getCode());
        map.put("message", ResultEnum.SUCCESS.getMessage());
        map.put("success",true);
        map.put("data",null);
        return null;
    }

    /**
     * 错误信息返回code，message，data
     * @Date 2019/6/20 23:24
     * @param code
     * @return map
     */
    public final static  Map<String, Object> failure(int code, String message,Object data) {
        Map<String, Object> map = new HashMap<>(6);
        map.put("code", code);
        map.put("message", message);
        map.put("data", data);
        map.put("success",false);
        return map;
    }

    /**
     * 错误信息返回code，message
     * @Date 2019/6/20 23:24
     * @param code
     * @return map
     */
    public final static  Map<String, Object> failure(int code, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", code);
        map.put("message", message);
        map.put("data", null);
        map.put("success",false);
        return map;
    }


}
