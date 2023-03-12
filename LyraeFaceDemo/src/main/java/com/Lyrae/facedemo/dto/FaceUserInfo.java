package com.Lyrae.facedemo.dto;

import com.Lyrae.facedemo.pojo.UserFaceInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author:Lyrae
 */
@Getter
@Setter
public class FaceUserInfo extends UserFaceInfo {

    private String faceId;
    private String name;
    private Integer similarValue;
    private byte[] faceFeature;
}
