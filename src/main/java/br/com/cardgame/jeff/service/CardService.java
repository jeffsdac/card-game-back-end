package br.com.cardgame.jeff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardgame.jeff.model.Card;
import br.com.cardgame.jeff.repository.CardRepository;
import jakarta.transaction.Transactional;

@Service
public class CardService{
    
    @Autowired
    private CardRepository cardRepo;

    @Transactional
    public Card saveCard (Card card) {
        return cardRepo.save(card);
    }

}
