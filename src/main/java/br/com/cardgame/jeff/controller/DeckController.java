package br.com.cardgame.jeff.controller;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardgame.jeff.dtos.DeckDtoCreate;
import br.com.cardgame.jeff.dtos.DeckDtoRegister;
import br.com.cardgame.jeff.dtos.DeckDtoWithCards;
import br.com.cardgame.jeff.model.Card;
import br.com.cardgame.jeff.model.Deck;
import br.com.cardgame.jeff.service.DeckService;
import br.com.cardgame.jeff.service.UserEntityService;

@RestController
@RequestMapping("/api/deck")
public class DeckController {

    @Autowired
    UserEntityService userService;

    @Autowired
    DeckService deckService;


    @PostMapping("/register")
    public ResponseEntity<DeckDtoCreate> createDeck(DeckDtoRegister deckDto){
        var userOwner = userService.findById(deckDto.userId());

        var deck = new Deck();
        deck.setCreatedIn(LocalDateTime.now());
        deck.setUser(userOwner);
        deckService.saveDeck(deck);

        return ResponseEntity.status(HttpStatus.CREATED).body(new DeckDtoCreate(deck.getCreatedIn()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeckDtoCreate> findById (@PathVariable int id){
        var deck = deckService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body( new DeckDtoCreate(deck.getCreatedIn()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDeck (@PathVariable int id){
        try{
            deckService.deleteDeck(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/{id}/updatecards")
    public ResponseEntity<DeckDtoWithCards> updateCards (@PathVariable int id, Set<Card> cards){
        try {
            var deck = deckService.updateCards(id, cards);
            return ResponseEntity.status(HttpStatus.OK).body(new DeckDtoWithCards(deck.getCards()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
