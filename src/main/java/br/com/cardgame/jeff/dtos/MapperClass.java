package br.com.cardgame.jeff.dtos;

import br.com.cardgame.jeff.model.UserEntity;

public class MapperClass {

    public static UserEntity UserEntityDtoToUserEntity (UserEntityDto dto){
        var usuario = new UserEntity();
        usuario.setEmail(dto.email());
        usuario.setFullname(dto.fullName());
        usuario.setPassword(dto.password());
        usuario.setUsername(dto.username());
        return usuario;
    }
}
