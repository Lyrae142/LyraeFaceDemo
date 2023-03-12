package com.Lyrae.facedemo.service.impl;

import com.Lyrae.facedemo.mapper.UserFaceInfoMapper;
import com.Lyrae.facedemo.pojo.UserFaceInfo;
import com.Lyrae.facedemo.service.UserFaceInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:Lyrae
 */
@Service("userFaceInfoService")
public class UserFaceInfoServiceImpl implements UserFaceInfoService {

    @Resource
    private UserFaceInfoMapper userFaceInfoMapper;

    /**
     * 新增用户信息
     * @param userFaceInfo
     * @return
     */
    @Override
    public int insertSelective(UserFaceInfo userFaceInfo) {
        int result = userFaceInfoMapper.insertUserFaceInfo(userFaceInfo);
        if (result > 0){
            return result;
        }

        return 0;
    }

    /**
     * 查询全部信息
     * @return
     */
    @Override
    public List<UserFaceInfo> findUserFaceInfoList() {
        return userFaceInfoMapper.findUserFaceInfoList();
    }
}
