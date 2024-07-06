package br.com.cardgame.jeff.exceptions;

public class DeckNotFoundException extends RuntimeException{
    public DeckNotFoundException (String msg){
        super(msg);
    }
    
}
