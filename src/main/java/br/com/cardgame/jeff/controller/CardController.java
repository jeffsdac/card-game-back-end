package br.com.cardgame.jeff.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardgame.jeff.dtos.CardRegisterDto;
import br.com.cardgame.jeff.dtos.CardSavedDto;
import br.com.cardgame.jeff.service.CardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
