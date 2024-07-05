package br.com.cardgame.jeff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cardgame.jeff.model.Deck;

public interface DeckRepository extends JpaRepository<Deck, Integer>{
}
