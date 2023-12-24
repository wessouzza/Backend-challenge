package com.user_register.user_register.service;

import com.user_register.user_register.exceptions.ErrorMsg;
import org.springframework.stereotype.Service;

import com.user_register.user_register.repository.UserRepository;
import com.user_register.user_register.user.GroupType;
import com.user_register.user_register.user.UserModel;
import com.user_register.user_register.userDto.UserDto;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final NicknameService nicknameService;

    public UserService(UserRepository userRepository, NicknameService nicknameService){
        this.userRepository = userRepository;
        this.nicknameService = nicknameService;
    }

    public UserModel addUser(UserDto dto){

        UserModel newUser = new UserModel(dto);

        if(dto.groupType() == GroupType.AVENGERS){
            String codiname = nicknameService.getAvengersList().stream().findFirst().orElseThrow();
            nicknameService.getAvengersList().remove(codiname);
            newUser.setNickName(codiname);
        }else{
            String codiname = nicknameService.getJusticeLeagueList().stream().findFirst().orElseThrow();
            nicknameService.getJusticeLeagueList().remove(codiname);
            newUser.setNickName(codiname);
        }

        if(nicknameService.getAvengersList().isEmpty() || nicknameService.getJusticeLeagueList().isEmpty()){
            throw new ErrorMsg("Não há mais codinomes disponíveis.");
        }

        return userRepository.save(newUser);
    }
}