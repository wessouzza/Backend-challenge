package com.user_register.user_register.user;

import com.user_register.user_register.userDto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "gammers")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Insira um username.")
    private String userName;
    @NotBlank(message = "Insira um email válido.")
    private String email;
    private int phoneNumber;
    private String nickName;
    @Enumerated(EnumType.STRING)
    private GroupType groupType;

    public UserModel(){}

    public UserModel(Long id, String userName, String email, int phoneNumber, String nickName, GroupType groupType){
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.groupType = groupType;
    }

    public UserModel(UserDto dto) {
        this.userName = dto.userName();
        this.email = dto.email();
        this.phoneNumber = dto.phoneNumber();
        this.groupType = dto.groupType();
    }


    public String getUserName() {
        return userName;
    }

    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    public String getEmail() {
        return email;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }

    
    public int getPhoneNumber() {
        return phoneNumber;
    }

    
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
    public String getNickName() {
        return nickName;
    }

    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    
    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType){
        this.groupType = groupType;
    }


}