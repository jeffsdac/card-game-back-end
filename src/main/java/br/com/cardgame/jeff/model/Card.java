package br.com.cardgame.jeff.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

@Entity
@Table( name = "T_CARD_CARD" )
public class Card {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    private int mana;
    private String description;
    private int ataque;
    private int defesa;

    @ManyToOne()
    @JoinColumn( name = "deck_id", nullable = false )
    private Deck deck;
}
