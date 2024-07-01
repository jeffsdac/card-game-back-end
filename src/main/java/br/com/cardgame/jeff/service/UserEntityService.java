package br.com.cardgame.jeff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardgame.jeff.model.UserEntityCard;
import br.com.cardgame.jeff.repository.UserEntityRepository;

@Service
public class UserEntityService {

    @Autowired
    private UserEntityRepository userRepo;

    public UserEntityCard saveUserEntity(UserEntityCard userInput){
        return userRepo.save(userInput);
    }

}
