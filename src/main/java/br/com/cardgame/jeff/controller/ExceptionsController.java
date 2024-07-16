package br.com.cardgame.jeff.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.cardgame.jeff.dtos.UserEntityNotValidDto;
import br.com.cardgame.jeff.exceptions.EmailAlreadyExists;
import br.com.cardgame.jeff.exceptions.EmailAndUsernameAlreadyExists;
import br.com.cardgame.jeff.exceptions.UsernameAlreadyExists;

@RestControllerAdvice
public class ExceptionsController {
    
    @ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<UserEntityNotValidDto> handleEmailExistsException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new UserEntityNotValidDto(true, false) );
    }

    @ExceptionHandler(UsernameAlreadyExists.class)
    public ResponseEntity<UserEntityNotValidDto> handleUsernameExistsException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new UserEntityNotValidDto(false, true) );
    }

    @ExceptionHandler(EmailAndUsernameAlreadyExists.class)
    public ResponseEntity<UserEntityNotValidDto> handleUsernameEmailExistsException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new UserEntityNotValidDto(true, true) );
    }

}
