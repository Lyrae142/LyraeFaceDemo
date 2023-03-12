package com.Lyrae.facedemo.service;

import com.Lyrae.facedemo.entity.User;
import com.Lyrae.facedemo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author:Lyrae
 */
public interface UserService {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 将数据封装到Map类型中
     */
    public Map<String, Object> queryInfoByUsername(String username);

    /**
     * 注册功能
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public boolean registerData(String username, String password);

}
