package br.com.cardgame.jeff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cardgame.jeff.model.RelDeckCard;

public interface RelDeckCardRepository extends JpaRepository<RelDeckCard, Integer> {
}
