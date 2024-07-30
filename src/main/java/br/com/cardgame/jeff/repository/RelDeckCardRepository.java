package br.com.cardgame.jeff.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cardgame.jeff.model.Deck;
import br.com.cardgame.jeff.model.RelDeckCard;

public interface RelDeckCardRepository extends JpaRepository<RelDeckCard, Integer> {
    
    public Optional<List<RelDeckCard>> findByDeck (Deck deck);

}
