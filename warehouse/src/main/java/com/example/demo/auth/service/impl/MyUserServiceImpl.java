package com.example.demo.auth.service.impl;

import com.example.demo.auth.entity.UserInfo;
import com.example.demo.auth.mapper.UserMapper;
import com.example.demo.auth.service.MyUserService;
import com.example.demo.auth.util.JwtTokenUtil;
import com.example.demo.common.ResponseData;
import com.example.demo.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserServiceImpl implements MyUserService {

    @Autowired
    private UserMapper userMapper;

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
        List<User> users = userMapper.selectAll();
        for(User user: users) {
            user.setPassword(user.getPassword());
        }
        return ResponseData.buildOk(users);
    }

    @Override
    public ResponseData insertUser(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        int i = userMapper.insertUser(user.getUsername(), password, user.getAccount());
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
    public ResponseData changePasswordByAccount(User user) {
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        int i = userMapper.changePasswordByAccount(user.getPassword(), user.getAccount());
        if (i > 0) return ResponseData.buildOk();
        return ResponseData.buildError();
    }
}
