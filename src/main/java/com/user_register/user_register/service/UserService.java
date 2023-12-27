package com.user_register.user_register.service;

import com.user_register.user_register.exceptions.ErrorMsg;
import org.springframework.stereotype.Service;

import com.user_register.user_register.repository.UserRepository;
import com.user_register.user_register.user.GroupType;
import com.user_register.user_register.user.UserModel;
import com.user_register.user_register.userDto.UserDto;

import java.util.List;
import java.util.Objects;

import static com.user_register.user_register.service.NicknameService.checkNicknameList;

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
        validateUser(dto);

        String codiname;
        if(dto.groupType() == GroupType.AVENGERS){
            codiname = nicknameService.getAvengersList().stream().findFirst().orElseThrow();
            nicknameService.getAvengersList().remove(codiname);
            checkNicknameList(nicknameService.getAvengersList());
        }else{
            codiname = nicknameService.getJusticeLeagueList().stream().findFirst().orElseThrow();
            nicknameService.getJusticeLeagueList().remove(codiname);
            checkNicknameList(nicknameService.getJusticeLeagueList());
        }
        newUser.setNickName(codiname);

        return userRepository.save(newUser);
    }

    public List<UserModel> listUsers(){
        return userRepository.findAll();
    }

    public void validateUser(UserDto dto){
        UserModel username = userRepository.findByUserName(dto.userName());
        UserModel userEmail = userRepository.findByEmail(dto.email());

        if(username != null || userEmail != null){
            throw new ErrorMsg("Usuário com e-mail ou username já cadastrado.");
        }

    }
}