package com.example.demo.auth.entity;

import com.example.demo.entity.Role;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class UserTable {
    private BigInteger id;
    private String account;
    private String password;
    private String username;
    private List<Role> roles;
}
