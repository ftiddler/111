package com.example.demo.entity;

import lombok.Data;

import java.math.BigInteger;

@Data
public class User {
    private BigInteger id;
    private String account;
    private String password;
    private String username;
}
