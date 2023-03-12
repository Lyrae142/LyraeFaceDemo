package com.Lyrae.facedemo.controller;

import com.Lyrae.facedemo.pojo.UserFaceInfo;
import com.Lyrae.facedemo.service.UserFaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Lyrae
 */
@RestController
public class UserFaceInfoController {

    @Resource
    private UserFaceInfoService userFaceInfoService;

    /**
     * 获取用户人脸信息
     * @return
     */
    @GetMapping("/userInfo")
    public List<UserFaceInfo> getUserInfo(){
        List<UserFaceInfo> list = new ArrayList<>();
        list = userFaceInfoService.findUserFaceInfoList();
        return list;
    }
}
