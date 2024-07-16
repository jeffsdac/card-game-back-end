package br.com.cardgame.jeff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardgame.jeff.dtos.DeckDtoCreate;
import br.com.cardgame.jeff.dtos.DeckDtoRegister;
import br.com.cardgame.jeff.dtos.MapperClass;
import br.com.cardgame.jeff.model.Card;
import br.com.cardgame.jeff.model.Deck;
import br.com.cardgame.jeff.repository.ArtsCardRepository;
import br.com.cardgame.jeff.repository.DeckRepository;
import br.com.cardgame.jeff.repository.UserEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class DeckService {
    
    @Autowired
    private DeckRepository deckRepo;

    @Autowired
    ArtsCardRepository artRepo;

    @Autowired
    private UserEntityRepository userRepo;

    public Deck findById (int id){
        var deckDb = deckRepo.findById(id).orElseThrow( () -> new RuntimeException("Could not found deck with this id"));

        return deckDb;
    }
    
    @Transactional
    public Deck saveDeck(DeckDtoRegister deckDto) {
        var user = userRepo.findById(deckDto.userId())
        .orElseThrow( () -> new EntityNotFoundException("Could not found user, consequently could not save the deck"));

        var image = artRepo.findById(deckDto.imageId())
        .orElseThrow( () -> new EntityNotFoundException("Could not found the art, consequently could not save the deck"));

        var deck = MapperClass.deckDtoCreateToDeck(deckDto, image, user);

        deckRepo.save(deck);

        return deck;
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

    public List<DeckDtoCreate> findDecksByUsername (String username){
        var user = userRepo.findByUsername(username)
        .orElseThrow( () -> new EntityNotFoundException("Could not found any user, and consequently any deck"));
        
        var decks = deckRepo.findByUser(user).get()
        .stream().map((deck) -> MapperClass.deckToDeckDtoGetByUser(deck)).toList();

        return decks;
    }
}
