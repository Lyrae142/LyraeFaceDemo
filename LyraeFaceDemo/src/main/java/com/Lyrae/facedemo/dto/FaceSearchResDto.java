package com.Lyrae.facedemo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author:Lyrae
 */
@Getter
@Setter
public class FaceSearchResDto {

    private String faceId;
    private String name;
    private Integer similarValue;
    private Integer age;
    private String gender;
    private String image;

}
