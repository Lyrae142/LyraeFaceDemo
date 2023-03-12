package com.Lyrae.facedemo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:Lyrae
 */
@Data
public class User implements Serializable {

    private String id;
    private String name;
    private String password;

}
