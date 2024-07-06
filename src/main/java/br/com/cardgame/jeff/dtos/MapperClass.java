package br.com.cardgame.jeff.dtos;
import java.util.HashSet;

import br.com.cardgame.jeff.model.ArtsCard;
import br.com.cardgame.jeff.model.Card;
import br.com.cardgame.jeff.model.Deck;
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

    public static Card CardRegisteDtoToCard (CardRegisterDto dto, ArtsCard art, Deck deck){
        var decks = new HashSet<Deck>();
        decks.add(deck);

        var card = new Card();
        card.setDeck(decks);
        card.setArt(art);
        card.setAtaque(dto.ataque());
        card.setDefesa(dto.defesa());
        card.setDescription(dto.description());
        card.setMana(dto.mana());
        return card;
    }

}
