package com.sw.hs.controller;

import com.sw.hs.data.entity.User;
import com.sw.hs.service.UserService;
import com.sw.hs.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")   // http://localhost:8080/api/user
public class UserController {

    @Autowired
    UserService userService;

    // 회원가입
    @PostMapping("/userSave")
    public User UserSave(@RequestBody User user){
        return userService.userSave(user);
    }
}
