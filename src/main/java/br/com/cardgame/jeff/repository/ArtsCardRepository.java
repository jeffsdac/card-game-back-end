package br.com.cardgame.jeff.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cardgame.jeff.model.ArtsCard;

public interface ArtsCardRepository extends JpaRepository<ArtsCard, Integer>{
    Optional<ArtsCard> findByName (String name);
}
