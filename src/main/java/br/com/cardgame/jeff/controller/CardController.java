package br.com.cardgame.jeff.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardgame.jeff.dtos.CardRegisterDto;
import br.com.cardgame.jeff.dtos.CardSavedDto;
import br.com.cardgame.jeff.dtos.CardUpdateDto;
import br.com.cardgame.jeff.exceptions.NoCardRegisteredException;
import br.com.cardgame.jeff.service.CardService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    private CardService cardService;
    
    @PostMapping
    public ResponseEntity<CardSavedDto> saveCard (@RequestBody CardRegisterDto cardDto) {

        var cardSaved = cardService.saveCard(cardDto);

        var dtoToInterface = new CardSavedDto(cardSaved.getMana(), cardSaved.getDescription(),
        cardSaved.getAtaque(), cardSaved.getDefesa(), cardSaved.getArt().getImageData());

        return ResponseEntity.status(HttpStatus.OK).body(dtoToInterface);
    }


    @GetMapping
    public ResponseEntity<List<CardSavedDto>> findAllCards(){
        try{
            var cards = cardService.findAllCards();
            return ResponseEntity.status(HttpStatus.OK).body(cards);
        }catch (NoCardRegisteredException noCard){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardSavedDto> findById (@PathVariable int id){
        var card = cardService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(card);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CardSavedDto> updateById (@PathVariable int id, @RequestBody CardUpdateDto dtoUpdate){
        var card = cardService.updateCard(dtoUpdate, id);
        return ResponseEntity.status(HttpStatus.OK).body(card);
    }
}
