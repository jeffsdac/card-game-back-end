package br.com.cardgame.jeff.exceptions;

public class EmailAlreadyExists extends RuntimeException {
    public EmailAlreadyExists(String msg){
        super(msg);
    }
}
