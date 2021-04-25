package com.example.demo.auth.service.impl;

import com.example.demo.auth.entity.UserInfo;
import com.example.demo.auth.mapper.UserMapper;
import com.example.demo.auth.service.MyUserService;
import com.example.demo.auth.util.JwtTokenUtil;
import com.example.demo.common.ResponseData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public ResponseData insertUser(String username, String password, String authority, String account) {
        password = passwordEncoder.encode(password);
        int i = userMapper.insertUser(username, password, authority, account);
        if (i > 0) return ResponseData.buildOk("添加成功");
        return ResponseData.buildError("添加失败");
    }
}
