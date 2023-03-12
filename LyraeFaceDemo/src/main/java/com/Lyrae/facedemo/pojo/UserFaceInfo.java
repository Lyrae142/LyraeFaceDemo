package com.Lyrae.facedemo.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author:Lyrae
 */
@Data
public class UserFaceInfo {

    private Integer id;

    private Integer groupId;

    private String faceId;

    private String name;

    private Integer age;

    private String email;

    private Short gender;

    private String phoneNumber;

    private Date createTime;

    private Date updateTime;

    private byte[] faceFeature;

}
