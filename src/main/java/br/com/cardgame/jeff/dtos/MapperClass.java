package br.com.cardgame.jeff.dtos;

import br.com.cardgame.jeff.model.UserEntityCard;

public class MapperClass {

    public static UserEntityCard UserEntityDtoToUserEntity (UserEntityDto dto){
        var usuario = new UserEntityCard();
        usuario.setEmail(dto.email());
        usuario.setFullname(dto.fullName());
        usuario.setPassword(dto.password());
        usuario.setUsername(dto.username());
        return usuario;
    }
}
