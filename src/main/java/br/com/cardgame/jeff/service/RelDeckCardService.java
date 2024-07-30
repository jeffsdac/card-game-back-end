package br.com.cardgame.jeff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardgame.jeff.model.RelDeckCard;
import br.com.cardgame.jeff.repository.CardRepository;
import br.com.cardgame.jeff.repository.DeckRepository;
import br.com.cardgame.jeff.repository.RelDeckCardRepository;
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
        var relantion = new RelDeckCard();
        var deck = deckRepo.findById(idDeck).get();
        var card = cardRepo.findById(idCard).get();

        relantion.setCard(card);
        relantion.setDeck(deck);

        return relRepo.save(relantion);
        
    }
    
}
