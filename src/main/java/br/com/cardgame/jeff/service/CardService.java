package br.com.cardgame.jeff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardgame.jeff.dtos.CardRegisterDto;
import br.com.cardgame.jeff.dtos.CardSavedDto;
import br.com.cardgame.jeff.dtos.MapperClass;
import br.com.cardgame.jeff.exceptions.ArtNotFoundException;
import br.com.cardgame.jeff.exceptions.DeckNotFoundException;
import br.com.cardgame.jeff.exceptions.NoCardRegisteredException;
import br.com.cardgame.jeff.model.Card;
import br.com.cardgame.jeff.repository.ArtsCardRepository;
import br.com.cardgame.jeff.repository.CardRepository;
import br.com.cardgame.jeff.repository.DeckRepository;
import jakarta.transaction.Transactional;

@Service
public class CardService{
    
    @Autowired
    private CardRepository cardRepo;

    @Autowired
    private ArtsCardRepository artsRepo;

    @Autowired
    private DeckRepository deckRepo;

    @Transactional
    public Card saveCard (CardRegisterDto cardDto) {
        var art = artsRepo.findById(cardDto.artId()).orElseThrow( () -> new ArtNotFoundException("Art could not be found with this id"));

        var deck = deckRepo.findById(cardDto.deckId()).orElseThrow( () -> new DeckNotFoundException("Deck could not be found with this id"));

        var card = MapperClass.CardRegisteDtoToCard(cardDto, art, deck);

        return cardRepo.save(card);
    }

    public List<CardSavedDto> findAllCards(){
        var allCards = cardRepo.findAll();

        if (allCards.isEmpty()) throw new NoCardRegisteredException("No card is registred");

        var allCardsdto = allCards.stream().map( (card) -> 
        MapperClass.cardToCardSavedDto(card)).toList();
        
        return allCardsdto;
    }

}
