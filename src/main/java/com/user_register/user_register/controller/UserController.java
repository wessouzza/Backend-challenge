package com.user_register.user_register.controller;

import com.user_register.user_register.exceptions.ErrorMsg;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.user_register.user_register.service.UserService;
import com.user_register.user_register.user.UserModel;
import com.user_register.user_register.userDto.UserDto;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDto userDto){
        try{
            UserModel newUser = userService.addUser(userDto);
            return ResponseEntity.ok(newUser);
        }catch (ErrorMsg msg){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg.getMessage());
        }
    }

    @GetMapping
    public List<UserModel> listUsers(){
        return userService.listUsers();
    }
}