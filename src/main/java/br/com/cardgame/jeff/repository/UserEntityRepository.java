package br.com.cardgame.jeff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cardgame.jeff.model.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer>{
}
