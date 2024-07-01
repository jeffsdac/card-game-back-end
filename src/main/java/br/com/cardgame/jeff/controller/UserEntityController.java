package br.com.cardgame.jeff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardgame.jeff.dtos.MapperClass;
import br.com.cardgame.jeff.dtos.UserEntityDto;
import br.com.cardgame.jeff.model.UserEntity;
import br.com.cardgame.jeff.service.UserEntityService;

@RestController
@RequestMapping("/api/user")
public class UserEntityController {

    @Autowired
    private UserEntityService userServ;

    @PostMapping
    public ResponseEntity<UserEntity> registerUser (@RequestBody UserEntityDto dtoUser){
        var userSaved = userServ.saveUserEntity(MapperClass.UserEntityDtoToUserEntity(dtoUser));
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }


}
