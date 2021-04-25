package com.example.demo.auth.service;

import com.example.demo.auth.entity.UserInfo;
import com.example.demo.common.ResponseData;

/**
 * @author GuoWei
 * @date 2021-3-20
 * @description
 * <p>
 *    通过请求头里的token获取这个http用户的信息
 * </p>
 */
public interface MyUserService {
    /**
     * 获取当前用户的信息
     * @param token token
     * @return 用户信息
     */
    UserInfo getInfo(String token);

    ResponseData insertUser(String username, String password, String authority, String account);
}
