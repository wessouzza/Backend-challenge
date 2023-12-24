package com.user_register.user_register.userDto;

import com.user_register.user_register.user.GroupType;

public record UserDto(String userName, String email, int phoneNumber, GroupType groupType) {
    
}