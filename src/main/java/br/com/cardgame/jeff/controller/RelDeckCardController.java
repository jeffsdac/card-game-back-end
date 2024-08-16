package br.com.cardgame.jeff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardgame.jeff.dtos.RelDeckCardReceiverDto;
import br.com.cardgame.jeff.dtos.RelDeckCardSenderDto;
import br.com.cardgame.jeff.dtos.RelDeckSaveAllDto;
import br.com.cardgame.jeff.dtos.RelDecksCardFullDto;
import br.com.cardgame.jeff.dtos.RelJustIdsDto;
import br.com.cardgame.jeff.model.RelDeckCard;
import br.com.cardgame.jeff.service.RelDeckCardService;

@RestController
@RequestMapping("/api/rel")
public class RelDeckCardController {

    @Autowired
    private RelDeckCardService relService;

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<RelDeckCardSenderDto> saveRel (@RequestBody  RelDeckCardReceiverDto dto){
        var rel = relService.saveRel(dto.deckId(), dto.cardId());
        var dtoSender = new RelDeckCardSenderDto(rel.getDeck().getId(), rel.getCard().getId());

        return ResponseEntity.status(HttpStatus.OK).body(dtoSender);
    }
    
    @GetMapping("/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<RelDecksCardFullDto>> getCardByDeckId (@PathVariable int id){
        var rel = relService.findByDeck(id);

        return ResponseEntity.status(HttpStatus.OK).body(rel);
    }

    @GetMapping("/justid/{deckId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<RelJustIdsDto>> findAllJustId (@PathVariable int deckId){
        var ids = relService.findAllJustId(deckId);
        return ResponseEntity.status(HttpStatus.OK).body(ids);
    }
    
    @PostMapping("/saverels")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<RelDeckCard>> saveAll (@RequestBody List<RelDeckSaveAllDto> relDtos ){
        List<RelDeckCard> rels = relService.saveAll(relDtos);
        return ResponseEntity.status(HttpStatus.OK).body(rels);
    } 

    @PatchMapping("/removeoneqtd/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<RelJustIdsDto> removeQtd (@PathVariable int id){
        var rel = relService.minusOneQtd(id);
        return ResponseEntity.status(HttpStatus.OK).body(rel);
    } 
}
