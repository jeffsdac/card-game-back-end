package br.com.cardgame.jeff.dtos;

import org.springframework.http.HttpStatus;

public record ExceptionDto(HttpStatus responseCode, String responseMessage) {
    
}
