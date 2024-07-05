package br.com.cardgame.jeff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cardgame.jeff.model.Card;

public interface CardRepository extends JpaRepository<Card, Integer>{
}
