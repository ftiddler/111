package com.example.demo.auth.entity;

import lombok.Data;

@Data
public class LoginInfo {
    private Integer id;
    private String account;
    private String username;
    private String password;
}
