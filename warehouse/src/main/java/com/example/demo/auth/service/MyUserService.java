package com.example.demo.auth.service;

import com.example.demo.auth.entity.UserInfo;
import com.example.demo.common.ResponseData;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;

import javax.xml.ws.Response;
import java.math.BigInteger;

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

    ResponseData selectAll();

    ResponseData selectRoleByAccount(String account);

    ResponseData selectAllDepartments();

    ResponseData selectAllPositions();

    ResponseData insertUser(User user);

    ResponseData insertUserRole(BigInteger userId, String departmentName, String positionName);

    ResponseData deleteByAccount(String account);

    ResponseData deleteUserRole(BigInteger userId, String departmentName, String positionName);

    ResponseData changePasswordByAccount(User user);
}
