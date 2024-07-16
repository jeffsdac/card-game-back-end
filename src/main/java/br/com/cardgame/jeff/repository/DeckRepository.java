package br.com.cardgame.jeff.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cardgame.jeff.model.Deck;
import br.com.cardgame.jeff.model.UserEntityCard;

public interface DeckRepository extends JpaRepository<Deck, Integer>{
    public Optional<Set<Deck>> findByUser(UserEntityCard user);
}
