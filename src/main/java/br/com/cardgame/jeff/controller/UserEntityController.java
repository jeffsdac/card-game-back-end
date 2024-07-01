package br.com.cardgame.jeff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardgame.jeff.dtos.MapperClass;
import br.com.cardgame.jeff.dtos.UserEntityDto;
import br.com.cardgame.jeff.model.UserEntityCard;
import br.com.cardgame.jeff.service.UserEntityService;

@RestController
@RequestMapping("/api/user")
public class UserEntityController {

    @Autowired
    private UserEntityService userServ;

    @Autowired
    PasswordEncoder passEncoder;

    @PostMapping
    public ResponseEntity<UserEntityCard> registerUser (@RequestBody UserEntityDto dtoUser){
        String password = passEncoder.encode(dtoUser.password());
        var usuarioCript = new UserEntityDto(dtoUser.email(), password, dtoUser.fullName(), dtoUser.username());
        var userSaved = userServ.saveUserEntity(MapperClass.UserEntityDtoToUserEntity(usuarioCript));
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }


}
