package br.com.cardgame.jeff.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cardgame.jeff.model.Card;
import br.com.cardgame.jeff.model.tipoArt.CardType;

public interface CardRepository extends JpaRepository<Card, Integer>{
    public Optional<List<Card>> findByCardType (CardType cardType);


    @Query(value = "SELECT c.id FROM t_card_card c",
    nativeQuery = true)
    List<Integer> findAllJustId();
}
