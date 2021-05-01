package com.example.demo.auth.controller;

import com.example.demo.auth.entity.UserInfo;
import com.example.demo.auth.service.MyUserService;
import com.example.demo.auth.util.JwtTokenUtil;
import com.example.demo.common.ResponseData;
import com.example.demo.common.ResultObject;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.math.BigInteger;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private MyUserService userService;
    @RequestMapping("/info")
    public ResultObject getUserInfo(@RequestHeader(JwtTokenUtil.TOKEN_HEADER) String token){
        UserInfo info = userService.getInfo(token);
        return ResultObject.successWithData(info);
    }

    @GetMapping("/selectAll")
    public ResponseData selectAll() {
        return userService.selectAll();
    }

    @PostMapping("/selectRoleByAccount")
    public ResponseData selectRoleByAccount(@RequestParam("account") String account) {
        return userService.selectRoleByAccount(account);
    }

    @GetMapping("/selectAllDepartments")
    public ResponseData selectAllDepartments() {
        return userService.selectAllDepartments();
    }

    @GetMapping("/selectAllPositions")
    public ResponseData selectAllPositions() { return userService.selectAllPositions(); }

    @PostMapping("/insertUser")
    public ResponseData insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @PostMapping("/insertUserRole")
    public ResponseData insertUserRole(@RequestParam("userId") BigInteger userId, @RequestParam("departmentName") String departmentName,
                                       @RequestParam("positionName") String positionName) {
        return userService.insertUserRole(userId, departmentName, positionName);
    }

    @PostMapping("/deleteByAccount")
    public ResponseData deleteByAccount(@RequestParam("account") String account) {
        return userService.deleteByAccount(account);
    }

    @PostMapping("/deleteUserRole")
    public ResponseData deleteUserRole(@RequestParam("userId") BigInteger userId, @RequestParam("departmentName") String departmentName,
                                       @RequestParam("positionName") String positionName) {
        return userService.deleteUserRole(userId, departmentName, positionName);
    }

    @PostMapping("/changePasswordByAccount")
    public ResponseData changePasswordByAccount(@RequestBody User user) {
        return userService.changePasswordByAccount(user);
    }
}
