package com.example.demo.auth.service.impl;

import com.example.demo.auth.mapper.RoleMapper;
import com.example.demo.auth.service.RoleService;
import com.example.demo.common.ResponseData;
import com.example.demo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public BigInteger insertRole(Role role) {
        BigInteger roleId = roleMapper.selectRoleIdByDescribe(role.getDescribe());
        if (roleId != null) {
            return roleId;
        }
        int i = roleMapper.insertRole(role.getDepartmentId(), role.getPositionId(), role.getDescribe());
        if (i == 0) System.out.println("insertRole失败");
        roleId = roleMapper.selectRoleIdByDescribe(role.getDescribe());
        return roleId;
    }

}
