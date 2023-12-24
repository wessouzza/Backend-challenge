package com.user_register.user_register.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user_register.user_register.service.UserService;
import com.user_register.user_register.user.UserModel;
import com.user_register.user_register.userDto.UserDto;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public UserModel addUser(@RequestBody UserDto userDto){
        UserModel newUser = userService.addUser(userDto); 
        return newUser;
    }
}