package com.example.demo.auth.service;

import com.example.demo.common.ResponseData;
import com.example.demo.entity.Role;

import java.math.BigInteger;

public interface RoleService {

    BigInteger insertRole(Role role);

}
