package com.Lyrae.facedemo.service;

import com.Lyrae.facedemo.pojo.UserFaceInfo;

import java.util.List;

/**
 * @Author:Lyrae
 */
public interface UserFaceInfoService {

    /**
     * 新增
     * @param userFaceInfo
     * @return
     */
    int insertSelective(UserFaceInfo userFaceInfo);

    /**
     * 查询全部信息
     * @return
     */
    List<UserFaceInfo> findUserFaceInfoList();
}
