package br.com.cardgame.jeff.exceptions;

public class NoCardRegisteredException extends RuntimeException {
    public NoCardRegisteredException(String msg){
        super(msg);
    }
}
