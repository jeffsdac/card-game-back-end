package br.com.cardgame.jeff.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicControllerAuthorization {


    @GetMapping("/")
    public String allHaveAcess(){
        return "<h1> Welcome </h1>";
    }

    @GetMapping("/user")
    public String userAndAdminHaveAcess () {
        return "<h1>Users and admin</h1>";
    }

    @GetMapping("/admin")
    public String justAdminHaveAcess () {
        return "<h1>Just admin</h1>";
    }
}
