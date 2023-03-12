package com.Lyrae.facedemo.mapper;

import com.Lyrae.facedemo.dto.FaceUserInfo;
import com.Lyrae.facedemo.pojo.UserFaceInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:Lyrae
 */
@Mapper
public interface UserFaceInfoMapper {

    /**
     * 获取用户全部信息
     * @return
     */
    List<UserFaceInfo> findUserFaceInfoList();

    /**
     * 新增用户信息
     * @param userFaceInfo
     * @return
     */
    int insertUserFaceInfo(UserFaceInfo userFaceInfo);

    /**
     * 根据分组id获取用户的信息
     * @param groupId
     * @return
     */
    List<FaceUserInfo> getUserFaceInfoByGroupId(Integer groupId);
}
