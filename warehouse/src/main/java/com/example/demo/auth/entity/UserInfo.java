package com.example.demo.auth.entity;

import lombok.Data;

@Data
public class UserInfo {
    private String name;
    private String[] permissions;
    // 头像地址
    private String avatar;
    private String introduction;

}
