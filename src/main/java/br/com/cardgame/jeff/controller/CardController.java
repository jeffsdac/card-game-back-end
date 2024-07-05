package br.com.cardgame.jeff.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardgame.jeff.model.Card;
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
    public ResponseEntity<Card> saveCard (@RequestBody Card card) {
        card = cardService.saveCard(card);
        return ResponseEntity.status(HttpStatus.OK).body(card);
    }

}
