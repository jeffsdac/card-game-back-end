package br.com.cardgame.jeff.dtos;
import java.time.LocalDateTime;
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

    public static CardSavedDto cardToCardSavedDto (Card card){
        var dto = new CardSavedDto(
            card.getMana(), 
            card.getDescription(),
            card.getAtaque(), 
            card.getDefesa(),
            card.getArt().getImageData());

        return dto;
    }

    public static Deck deckDtoCreateToDeck (DeckDtoRegister dto, ArtsCard art, UserEntityCard user){
        
        var deck = new Deck();
        deck.setArt(art);
        deck.setUser(user);
        deck.setName(dto.name());
        deck.setCreatedIn(LocalDateTime.now());

        return deck;
    }

    public static DeckDtoCreate deckToDeckDtoGetByUser(Deck deck) {
        var dto = new DeckDtoCreate(deck.getCreatedIn(), deck.getArt().getImageData(), deck.getName());

        return dto;
    }


}
