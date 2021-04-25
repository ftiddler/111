package com.example.demo.auth.controller;

import com.example.demo.auth.entity.UserInfo;
import com.example.demo.auth.service.MyUserService;
import com.example.demo.auth.util.JwtTokenUtil;
import com.example.demo.common.ResponseData;
import com.example.demo.common.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/insert")
    public ResponseData insertUser(@RequestParam("username") String username, @RequestParam("password") String password,
    @RequestParam("authority") String authority, @RequestParam("account") String account) {
        return userService.insertUser(username, password, authority, account);
    }
}
