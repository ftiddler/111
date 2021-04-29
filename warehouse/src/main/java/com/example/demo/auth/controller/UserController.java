package com.example.demo.auth.controller;

import com.example.demo.auth.entity.UserInfo;
import com.example.demo.auth.service.MyUserService;
import com.example.demo.auth.util.JwtTokenUtil;
import com.example.demo.common.ResponseData;
import com.example.demo.common.ResultObject;
import com.example.demo.entity.User;
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

    @GetMapping("/selectAll")
    public ResponseData selectAll() {
        return userService.selectAll();
    }

    @PostMapping("/insertUser")
    public ResponseData insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @PostMapping("/deleteByAccount")
    public ResponseData deleteByAccount(@RequestParam("account") String account) {
        return userService.deleteByAccount(account);
    }

    @PostMapping("/changePasswordByAccount")
    public ResponseData changePasswordByAccount(@RequestBody User user) {
        return userService.changePasswordByAccount(user);
    }
}
