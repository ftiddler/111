package com.example.demo.auth.service.impl;

import com.example.demo.auth.mapper.*;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        if (account == null || "".equals(account))
        {
            throw new RuntimeException("用户不能为空");
        }
        // 调用方法查询用户
        User user = userMapper.findByAccount(account);
        if (user == null)
        {
            throw new RuntimeException("用户不存在");
        }
        //获取用户的所有角色
        List<Role> roles = roleMapper.selectRoleByAccount(account);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //获取用户的所有权限
        for (Role role: roles) {
            List<BigInteger> selectedAuthorities = authorityMapper.selectAuthorityIdByRoleId(role.getId());
            selectedAuthorities.forEach(a->{authorities.add(new SimpleGrantedAuthority(a.toString()));});
        }
        return new org.springframework.security.core.userdetails.User(user.getAccount(),user.getPassword(),authorities);
    }
}