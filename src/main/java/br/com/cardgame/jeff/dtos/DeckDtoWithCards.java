package br.com.cardgame.jeff.dtos;

import java.util.Set;

import br.com.cardgame.jeff.model.Card;

public record DeckDtoWithCards (Set<Card> cards) {    
    
}
