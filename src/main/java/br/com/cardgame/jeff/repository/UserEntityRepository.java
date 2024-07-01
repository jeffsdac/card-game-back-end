package br.com.cardgame.jeff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cardgame.jeff.model.UserEntityCard;

public interface UserEntityRepository extends JpaRepository<UserEntityCard, Integer>{
}
