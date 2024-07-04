package br.com.cardgame.jeff.dtos;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String acessToken;
    private String tokenType;

    public AuthResponseDto(String acessToken){
        this.acessToken = acessToken;
    }
}   
