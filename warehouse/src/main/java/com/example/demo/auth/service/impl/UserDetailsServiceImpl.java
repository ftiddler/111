package com.example.demo.auth.service.impl;

import com.example.demo.auth.entity.Identity;
import com.example.demo.auth.entity.LoginInfo;
import com.example.demo.auth.mapper.AuthUserMapper;
import com.example.demo.auth.mapper.AuthorityMapper;
import com.example.demo.auth.mapper.IdentityMapper;
import com.example.demo.auth.mapper.UserMapper;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username))
        {
            throw new RuntimeException("用户不能为空");
        }
        // 调用方法查询用户
        User user = userMapper.findByAccount(username);
        if (user == null)
        {
            throw new RuntimeException("用户不存在");
        }

        String authority = user.getAuthority();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(authority));
        return new org.springframework.security.core.userdetails.User(user.getAccount(),user.getPassword(),authorities);
    }
}