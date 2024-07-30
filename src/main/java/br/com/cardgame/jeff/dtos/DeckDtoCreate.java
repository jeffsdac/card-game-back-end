package br.com.cardgame.jeff.dtos;

import java.time.LocalDateTime;

public record DeckDtoCreate(LocalDateTime createIn, byte[] image64, String imgType,int id ,String name){
    
}
