package br.com.cardgame.jeff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardgame.jeff.dtos.MapperClass;
import br.com.cardgame.jeff.dtos.RelDeckCardSenderDto;
import br.com.cardgame.jeff.dtos.RelDecksCardFullDto;
import br.com.cardgame.jeff.model.RelDeckCard;
import br.com.cardgame.jeff.repository.CardRepository;
import br.com.cardgame.jeff.repository.DeckRepository;
import br.com.cardgame.jeff.repository.RelDeckCardRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class RelDeckCardService {

    @Autowired
    private RelDeckCardRepository relRepo;

    @Autowired
    private DeckRepository deckRepo;

    @Autowired
    private CardRepository cardRepo;


    @Transactional
    public RelDeckCard saveRel (int idDeck, int idCard){

        var ship = new RelDeckCard();

        var deck = deckRepo.findById(idDeck).orElseThrow(
        () -> new EntityNotFoundException("Could not found any deck with this id"));
        
        
        var card = cardRepo.findById(idCard).orElseThrow(
        () -> new EntityNotFoundException("Could not found any card with this id"));

        ship.setCard(card);
        ship.setDeck(deck);

        return relRepo.save(ship);
        
    }

    @Transactional
    public List<RelDecksCardFullDto> findByDeck (int deckId){
        var deck = deckRepo.findById(deckId).orElseThrow( 
        () -> new EntityNotFoundException("Could not found any deck with this id"));

        var deckRel = relRepo.findByDeck(deck).orElseThrow(
        ()-> new EntityNotFoundException("Could not found any card with this deck"));
        
        var dtos = deckRel.stream()
        .map( (rel) -> MapperClass.relDeckCardToRelDecksCardFullDto(rel)).toList();

        return dtos;
    }
    
}
