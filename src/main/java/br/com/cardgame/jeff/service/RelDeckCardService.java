package br.com.cardgame.jeff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardgame.jeff.dtos.MapperClass;
import br.com.cardgame.jeff.dtos.RelDeckSaveAllDto;
import br.com.cardgame.jeff.dtos.RelDecksCardFullDto;
import br.com.cardgame.jeff.dtos.RelJustIdsDto;
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

        var rel = relRepo.findByDeckAndCard(deck, card);

        if (!(rel.isPresent())){
            ship.setTimesRelacted(1);
            ship.setCard(card);
            ship.setDeck(deck);
            return relRepo.save(ship);
        }

        var relan = rel.get();
        relan.setTimesRelacted(relan.getTimesRelacted() + 1);
        return relan;
        
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

    @Transactional
    public List<RelJustIdsDto> findAllJustId (int idDeck){
        var deck = deckRepo.findById(idDeck).get();

        var deckRel = relRepo.findByDeck(deck).orElseThrow(
        ()-> new EntityNotFoundException("Could not found any card with this deck"));

        var dtos = deckRel.stream()
        .map( (rel) -> MapperClass.relToRelJustIdsDto(rel)).toList();

        return dtos;

    } 

    // A coisa mais tenebrosa que eu já fiz faz algum tempo!
    @Transactional
    public List<RelDeckCard> saveAll (List<RelDeckSaveAllDto> relsDto){
        // Checar se o relacionamento já existe!
        ArrayList<RelDeckCard> noExist = new ArrayList<>();

        // Já existe? só dá update
        for (RelDeckSaveAllDto dto : relsDto){
            var deck = deckRepo.findById(dto.idDeck()).get();
            var card = cardRepo.findById(dto.idCard()).get();
            boolean existsRel = false;
            var cardsInDeck = deck.getRelDeckCard();
            
            for (RelDeckCard rel : cardsInDeck){
                if (rel.getCard().getId() == card.getId()){
                    rel.setTimesRelacted(rel.getTimesRelacted() + dto.qtd());
                    relRepo.save(rel);
                    existsRel = true;
                    break;
                }
            }
            if(existsRel) continue;

            var realRel = MapperClass.RelDeckSaveAllDtoToRelDeckCard(dto, deck, card);
            noExist.add(realRel);
        }

        return relRepo.saveAll(noExist);
    }

    @Transactional
    public RelJustIdsDto minusOneQtd (int idRel){
        RelDeckCard rel = relRepo.findById(idRel).orElseThrow(
        () -> new EntityNotFoundException("Could not found any rel with this id"));
        
        rel.setTimesRelacted(rel.getTimesRelacted() - 1);

        var rRel = relRepo.save(rel);
        return MapperClass.relToRelJustIdsDto(rRel);
        
    }
}
