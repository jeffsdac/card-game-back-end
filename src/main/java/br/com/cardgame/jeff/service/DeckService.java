package br.com.cardgame.jeff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardgame.jeff.model.Card;
import br.com.cardgame.jeff.model.Deck;
import br.com.cardgame.jeff.repository.DeckRepository;
import java.util.Set;

@Service
public class DeckService {
    
    @Autowired
    private DeckRepository deckRepo;

    public Deck findById (int id){
        var deckDb = deckRepo.findById(id).orElseThrow( () -> new RuntimeException("Could not found deck with this id"));

        return deckDb;
    }
    
    public Deck saveDeck(Deck deck) {
        return deckRepo.save(deck);
    }

    public boolean deleteDeck(int id){
        var deckDb = deckRepo.findById(id).orElseThrow( () -> new RuntimeException("Could not found deck with this id"));

       deckRepo.delete(deckDb);

       return true;
    }

    public Deck updateCards(int id, Set<Card> cardList){
        var deckDb = deckRepo.findById(id).orElseThrow( () -> new RuntimeException("Could not found deck with this id"));
        deckDb.setCards(cardList); 
        return deckRepo.save(deckDb);
    }



}
