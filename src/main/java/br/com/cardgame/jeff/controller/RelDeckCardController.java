package br.com.cardgame.jeff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardgame.jeff.dtos.RelDeckCardReceiverDto;
import br.com.cardgame.jeff.dtos.RelDeckCardSenderDto;
import br.com.cardgame.jeff.service.RelDeckCardService;

@RestController
@RequestMapping("/api/rel")
public class RelDeckCardController {

    @Autowired
    private RelDeckCardService relService;

    @PostMapping
    public ResponseEntity<RelDeckCardSenderDto> saveRel (@RequestBody  RelDeckCardReceiverDto dto){
        var rel = relService.saveRel(dto.deckId(), dto.cardId());
        var dtoSender = new RelDeckCardSenderDto(rel.getDeck().getId(), rel.getCard().getId());

        return ResponseEntity.status(HttpStatus.OK).body(dtoSender);
    }
    
}