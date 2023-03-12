package com.Lyrae.facedemo.service.impl;

import com.Lyrae.facedemo.mapper.UserMapper;
import com.Lyrae.facedemo.service.UserService;
import com.Lyrae.facedemo.utils.UUIDUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Lyrae
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    //将dao层属性注入service层
    @Resource
    private UserMapper userMapper;

    @Override
    public Map<String, Object> queryInfoByUsername(String username) {
        return userMapper.queryInfoByUsername(username);
    }

    /**
     * 实现注册操作以及加密数据
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public boolean registerData(String username, String password) {
        // 生成uuid
        String id = UUIDUtil.getOneUUID();

        // 将用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(username);
        /*
         * MD5加密：
         * 使用SimpleHash类对原始密码进行加密。
         * 第一个参数代表使用MD5方式加密
         * 第二个参数为原始密码
         * 第三个参数为盐值，即用户名
         * 第四个参数为加密次数
         * 最后用toHex()方法将加密后的密码转成String
         * */
        String newPassword = new SimpleHash("MD5", password, salt, 1024).toHex();

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("id", id);
        dataMap.put("username", username);
        dataMap.put("password", newPassword);

        // 看数据库中是否存在该账户
        Map<String, Object> userInfo = queryInfoByUsername(username);
        if(userInfo == null) {
            userMapper.insertData(dataMap);
            return true;
        }
        return false;
    }

}
