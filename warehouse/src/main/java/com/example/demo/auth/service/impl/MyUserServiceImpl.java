package com.example.demo.auth.service.impl;

import com.example.demo.auth.entity.UserInfo;
import com.example.demo.auth.entity.UserTable;
import com.example.demo.auth.mapper.RoleMapper;
import com.example.demo.auth.mapper.UserMapper;
import com.example.demo.auth.service.MyUserService;
import com.example.demo.auth.service.RoleService;
import com.example.demo.auth.util.JwtTokenUtil;
import com.example.demo.common.ResponseData;
import com.example.demo.entity.Department;
import com.example.demo.entity.Position;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserServiceImpl implements MyUserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfo getInfo(String tokenHeader) {
        // 去掉前缀 获取Token字符串
        String token = tokenHeader.replace(JwtTokenUtil.TOKEN_PREFIX, "");
        // 从Token中解密获取用户名
        String username = JwtTokenUtil.getUsername(token);
        String userPermissions = JwtTokenUtil.getPermissions(token);

        String[] permissions = StringUtils.strip(userPermissions , "[]").split(", ");

        UserInfo userInfo = new UserInfo();
        userInfo.setName(username);
        userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userInfo.setIntroduction("");
        userInfo.setPermissions(permissions);
        return userInfo;
    }

    @Override
    public ResponseData selectAll() {
        List<UserTable> users = userMapper.selectAll();
        for (UserTable user: users) {
            user.setRoles(roleMapper.selectRoleByAccount(user.getAccount()));
        }
        return ResponseData.buildOk(users);
    }

    @Override
    public ResponseData selectRoleByAccount(String account) {
        List<Role> roles = roleMapper.selectRoleByAccount(account);
        return ResponseData.buildOk(roles);
    }

    @Override
    public ResponseData selectAllDepartments() {
        List<Department> departments = userMapper.selectAllDepartments();
        return ResponseData.buildOk(departments);
    }

    @Override
    public ResponseData selectAllPositions() {
        List<Position> positions = userMapper.selectAllPositions();
        return ResponseData.buildOk(positions);
    }

    @Override
    public ResponseData insertUser(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        int i = userMapper.insertUser(user.getUsername(), password, user.getAccount());
        if (i > 0) return ResponseData.buildOk();
        return ResponseData.buildError();
    }

    @Override
    public ResponseData insertUserRole(BigInteger userId, String departmentName, String positionName) {
        Role role = new Role();
        role.setDepartmentId(roleMapper.selectDepartmentIdByName(departmentName));
        role.setPositionId(roleMapper.selectPositionIdByName(positionName));
        role.setDescribe(departmentName + positionName);
        BigInteger roleId = roleService.insertRole(role);
        int i = userMapper.insertUserRole(userId, roleId);
        if (i > 0) return ResponseData.buildOk();
        return ResponseData.buildError();
    }

    @Override
    public ResponseData deleteByAccount(String account) {
        int i = userMapper.deleteByAccount(account);
        if (i > 0) return ResponseData.buildOk();
        return ResponseData.buildError();
    }

    @Override
    public ResponseData deleteUserRole(BigInteger userId, String departmentName, String positionName) {
        int i = userMapper.deleteUserRole(userId, departmentName + positionName);
        if (i > 0) return ResponseData.buildOk();
        return ResponseData.buildError();
    }

    @Override
    public ResponseData changePasswordByAccount(User user) {
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        int i = userMapper.changePasswordByAccount(user.getPassword(), user.getAccount());
        if (i > 0) return ResponseData.buildOk();
        return ResponseData.buildError();
    }
}
