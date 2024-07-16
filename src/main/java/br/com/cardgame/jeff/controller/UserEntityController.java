package br.com.cardgame.jeff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardgame.jeff.dtos.AuthResponseDto;
import br.com.cardgame.jeff.dtos.MapperClass;
import br.com.cardgame.jeff.dtos.UserEntityDto;
import br.com.cardgame.jeff.dtos.UserEntityLoginDto;
import br.com.cardgame.jeff.model.UserEntityCard;
import br.com.cardgame.jeff.security.JWTGenerator;
import br.com.cardgame.jeff.service.UserEntityService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class UserEntityController {

    @Autowired
    private UserEntityService userServ;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passEncoder;

    @Autowired
    JWTGenerator jwtGenerator;

    @PostMapping("/register")
    public ResponseEntity<UserEntityCard> registerUser (@RequestBody UserEntityDto dtoUser){
        String password = passEncoder.encode(dtoUser.password());
        var usuarioCript = new UserEntityDto(dtoUser.email(), password, dtoUser.fullName(), dtoUser.username());
        var userSaved = userServ.saveUserEntity(MapperClass.UserEntityDtoToUserEntity(usuarioCript));
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login (@RequestBody UserEntityLoginDto userDto){
        var auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                userDto.username(),
                userDto.password()));
        
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtGenerator.generateToken(auth);
        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponseDto(token));
    }


}
