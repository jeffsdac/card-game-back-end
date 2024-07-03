package br.com.cardgame.jeff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cardgame.jeff.model.UserEntityCard;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntityCard, Integer>{
}
