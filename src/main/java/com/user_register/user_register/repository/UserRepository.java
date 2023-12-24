package com.user_register.user_register.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user_register.user_register.user.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{
    
}