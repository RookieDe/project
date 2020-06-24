package com.project.project5.service;

import com.project.project5.entity.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface ProjectPermissionFeign {

    /**
     * 接入
     *
     * @param device 设备类型
     * @param userId 用户id
     * @return CommonResponse
     */
    @GetMapping(value = "/docking/checkToken/Project",consumes = {MediaType.APPLICATION_JSON_VALUE})
    Response checkAuthority(@RequestHeader(value = "device") String device,
                            @RequestParam("userId")Long userId);
}
