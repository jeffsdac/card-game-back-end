package br.com.cardgame.jeff.exceptions;

public class UsernameAlreadyExists extends RuntimeException {
    public UsernameAlreadyExists(String msg){
        super(msg);
    }
}
