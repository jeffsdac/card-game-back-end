package br.com.cardgame.jeff.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cardgame.jeff.model.UserEntity;
import br.com.cardgame.jeff.repository.UserEntityRepository;

public class UserEntityService {

    @Autowired
    private UserEntityRepository userRepo;

    public UserEntity saveUserEntity(UserEntity userInput){
        return userRepo.save(userInput);
    }

}
