package br.com.cardgame.jeff.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "T_CARD_USER" )
public class UserEntityCard {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    private String email;
    private String password;
    private String fullname;
    private String username;
}
