package br.com.cardgame.jeff.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.cardgame.jeff.dtos.ExceptionDto;
import br.com.cardgame.jeff.dtos.UserEntityNotValidDto;
import br.com.cardgame.jeff.exceptions.EmailAlreadyExists;
import br.com.cardgame.jeff.exceptions.EmailAndUsernameAlreadyExists;
import br.com.cardgame.jeff.exceptions.NoCardRegisteredException;
import br.com.cardgame.jeff.exceptions.UsernameAlreadyExists;
import jakarta.persistence.EntityNotFoundException;

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

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ExceptionDto> handleIOExcpetion(){
        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(status)
        .body(new ExceptionDto(status, "Could not convert Image"));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleEntityNotFoundException (){
        var status = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status)
        .body( new ExceptionDto(status, "Could not found anything in Database for this request."));
    }

    @ExceptionHandler(NoCardRegisteredException.class)
    public ResponseEntity<ExceptionDto> handleNoCardRegister() {
        var status = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status)
        .body( new ExceptionDto(status, "Could not found any imagem, do you upload something?"));
    }

}
