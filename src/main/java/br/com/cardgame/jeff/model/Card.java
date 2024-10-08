package br.com.cardgame.jeff.model;

import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.cardgame.jeff.model.tipoArt.CardType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

@Entity
@Table( name = "T_CARD_CARD" )
@Builder
public class Card {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    private int mana;
    private String description;
    private int attack;
    private int healthPoints;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    private String tittle;
    private String lore;

    @ManyToOne( fetch = FetchType.LAZY )
    @Fetch(FetchMode.JOIN)
    @JoinColumn( name = "art_id", nullable = false )
    private ArtsCard art;

    @OneToMany( mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RelDeckCard> relDeckCard;
}
