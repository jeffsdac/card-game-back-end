package br.com.cardgame.jeff.exceptions;

public class EmailAndUsernameAlreadyExists extends RuntimeException {
    public EmailAndUsernameAlreadyExists (String msg){
        super(msg);
    }
}
