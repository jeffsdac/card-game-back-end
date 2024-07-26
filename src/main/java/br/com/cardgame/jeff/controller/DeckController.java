package br.com.cardgame.jeff.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardgame.jeff.dtos.DeckDtoCreate;
import java.util.List;
import br.com.cardgame.jeff.dtos.DeckDtoRegister;
import br.com.cardgame.jeff.dtos.DeckDtoWithCards;
import br.com.cardgame.jeff.dtos.MapperClass;
import br.com.cardgame.jeff.model.Card;
import br.com.cardgame.jeff.model.Deck;
import br.com.cardgame.jeff.service.DeckService;

@RestController
@RequestMapping("/api/deck")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class DeckController {


    @Autowired
    DeckService deckService;


    @PostMapping("/register")
    public ResponseEntity<DeckDtoCreate> createDeck(DeckDtoRegister deckDto){
        // System.out.println(deckDto.imageId());
        var deck = deckService.saveDeck(deckDto);
        return ResponseEntity.status(HttpStatus.OK).body(MapperClass.deckToDeckDtoGetByUser(deck));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeckDtoCreate> findById (@PathVariable int id){
        var deck = deckService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body( MapperClass.deckToDeckDtoGetByUser(deck) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Deck> deleteDeck (@PathVariable int id){
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

    @GetMapping("user/{username}")
    public ResponseEntity<List<DeckDtoCreate>> findByUsername (@PathVariable String username){
        var decks = deckService.findDecksByUsername(username);

        return ResponseEntity.status(HttpStatus.OK).body(decks);
    }
}
