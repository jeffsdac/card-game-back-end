package br.com.cardgame.jeff.dtos;
import br.com.cardgame.jeff.model.UserEntityCard;

public class MapperClass {

    public static UserEntityCard UserEntityDtoToUserEntity (UserEntityDto dto){
        var usuario = new UserEntityCard();
        usuario.setEmail(dto.email());
        usuario.setFullname(dto.fullName());
        usuario.setUsername(dto.username());
        usuario.setPassword(dto.password());
        return usuario;
    }
}
