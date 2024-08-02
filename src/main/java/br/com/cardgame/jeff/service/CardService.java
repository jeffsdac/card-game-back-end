package br.com.cardgame.jeff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardgame.jeff.dtos.CardRegisterDto;
import br.com.cardgame.jeff.dtos.CardSavedDto;
import br.com.cardgame.jeff.dtos.CardUpdateDto;
import br.com.cardgame.jeff.dtos.MapperClass;
import br.com.cardgame.jeff.exceptions.ArtNotFoundException;
import br.com.cardgame.jeff.exceptions.DeckNotFoundException;
import br.com.cardgame.jeff.exceptions.NoCardRegisteredException;
import br.com.cardgame.jeff.model.Card;
import br.com.cardgame.jeff.repository.ArtsCardRepository;
import br.com.cardgame.jeff.repository.CardRepository;
import br.com.cardgame.jeff.repository.DeckRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public CardSavedDto findById (int id){
        var card = MapperClass.cardToCardSavedDto(cardRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not found card with this id")));

        return card;
    }

    @Transactional
    public CardSavedDto updateCard(CardUpdateDto card, int id){
        var cardDb = cardRepo.findById(id).orElseThrow( () -> new EntityNotFoundException("Could not found card with this id"));

        cardDb.setAttack(card.ataque());
        cardDb.setDescription(card.description());
        cardDb.setMana(card.mana());

        cardRepo.save(cardDb);

        return MapperClass.cardToCardSavedDto(cardDb);
    }

    @Transactional
    public boolean deleteCard (int id){
        var card = cardRepo.findById(id).orElseThrow( () -> new EntityNotFoundException("Could not found card with this id"));

        cardRepo.delete(card);
        return true;
    }

    public List<CardSavedDto> findAll(){
        var cards = cardRepo.findAll();

        var dtos = cards.stream().map( 
        (card) -> MapperClass.cardToCardSavedDto(card)).toList();

        return dtos;
    }
}
