package br.com.cardgame.jeff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardgame.jeff.exceptions.EmailAlreadyExists;
import br.com.cardgame.jeff.exceptions.EmailAndUsernameAlreadyExists;
import br.com.cardgame.jeff.exceptions.UsernameAlreadyExists;
import br.com.cardgame.jeff.model.UserEntityCard;
import br.com.cardgame.jeff.repository.UserEntityRepository;

@Service
public class UserEntityService {

    @Autowired
    private UserEntityRepository userRepo;

    public UserEntityCard saveUserEntity(UserEntityCard userInput){
        var emailRegister = userRepo.existsByEmail(userInput.getEmail());
        var usernameRegister = userRepo.existsByUsername(userInput.getUsername());

        if (emailRegister && !usernameRegister) throw new EmailAlreadyExists("Email is already resgister");
        if (!emailRegister && usernameRegister) throw new UsernameAlreadyExists("Username is already register");
        if (emailRegister && usernameRegister) throw new EmailAndUsernameAlreadyExists ("Username and email is already register");

        return userRepo.save(userInput);
    }

    public UserEntityCard findById (int id){
        return userRepo.findById(id).orElseThrow( () -> new RuntimeException("Could not found user with this id"));
    }

}
