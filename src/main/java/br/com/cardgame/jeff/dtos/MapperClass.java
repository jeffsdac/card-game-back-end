package br.com.cardgame.jeff.dtos;
import java.time.LocalDateTime;
import java.util.HashSet;

import br.com.cardgame.jeff.model.ArtsCard;
import br.com.cardgame.jeff.model.Card;
import br.com.cardgame.jeff.model.Deck;
import br.com.cardgame.jeff.model.RelDeckCard;
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
        card.setLore(dto.lore());
        card.setTittle(dto.tittle());
        card.setArt(art);
        card.setAttack(dto.ataque());
        card.setHealthPoints(dto.defesa());
        card.setDescription(dto.description());
        card.setMana(dto.mana());
        card.setCardType(dto.cardType());
        return card;
    }

    
    public static CardSavedDto cardToCardSavedDto (Card card){
        var dto = new CardSavedDto(
        card.getMana(),
        card.getDescription(),
        card.getAttack(), 
        card.getHealthPoints(),
        card.getLore(),
        card.getTittle(), 
        card.getArt().getImageData(),
        card.getId());
        

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
        var imgType = deck.getArt().getType().split("/")[1];

        var dto = new DeckDtoCreate(
        deck.getCreatedIn(), 
        deck.getArt().getImageData(), 
        imgType ,
        deck.getId() ,
        deck.getName());
        return dto;
    }

    public static RelDeckCardSenderDto relDeckToRelDeckSenderDto (RelDeckCard relDeck){
        return new RelDeckCardSenderDto(
        relDeck.getDeck().getId(), 
        relDeck.getCard().getId());
    }

    public static ArtUploadDto artsCardToArtUploadDto (ArtsCard card) {
        return new ArtUploadDto(
        card.getType(), 
        card.getImageData(), 
        card.getId());
    }

    public static ArtSendDto artToArtSendDto (ArtsCard arts){
        return new ArtSendDto(
        arts.getType(), 
        arts.getImageData(),
        arts.getId());
    }

    public static  CardReturnRegisterDto cardToCardReturnRegister (Card card){
        return new CardReturnRegisterDto(card.getMana(),
        card.getDescription(),
        card.getAttack(),
        card.getHealthPoints(), 
        card.getLore(), 
        card.getTittle());
    } 

    public static RelDecksCardFullDto relDeckCardToRelDecksCardFullDto (RelDeckCard rel){
        return new RelDecksCardFullDto(rel.getCard().getMana(),
        rel.getCard().getDescription(),
        rel.getCard().getAttack(),
        rel.getCard().getHealthPoints(),
        rel.getCard().getLore(),
        rel.getCard().getTittle(),
        rel.getCard().getArt().getImageData(),
        rel.getCard().getId());
    }

    public static RelJustIdsDto relToRelJustIdsDto (RelDeckCard rel) {
        return new RelJustIdsDto(rel.getCard().getId(), rel.getTimesRelacted(), rel.getId());
    }

    public static RelDeckCard RelDeckSaveAllDtoToRelDeckCard (RelDeckSaveAllDto dto, Deck deck, Card card){
        return RelDeckCard.builder()
        .card(card)
        .deck(deck)
        .timesRelacted(dto.qtd())
        .build();
    }
}
