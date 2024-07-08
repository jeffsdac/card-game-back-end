package br.com.cardgame.jeff.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "art_id", nullable = false )
    private ArtsCard art;

    @ManyToMany( mappedBy = "cards", fetch = FetchType.LAZY )
    private Set<Deck> deck = new HashSet<>();
}
