package com.Lyrae.facedemo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @Author:Lyrae
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 将数据封装到Map类型中
     */
    public Map<String, Object> queryInfoByUsername(String username);

    /**
     * 插入一条数据
     * @param data Map中包含id,username,password
     */
    public void insertData(Map<String, String> data);
}
