package br.com.cardgame.jeff.model;



import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table( name = "T_CARD_DECK" )
public class Deck {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private LocalDateTime createdIn = LocalDateTime.now();

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntityCard user;


    // Arrumar que sempre precisa associar a um Deck
    @ManyToMany ( cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinTable ( name = "T_CARDS_DECK_AND_CARDS",
                 joinColumns = @JoinColumn ( name = "deck_id" ),
                 inverseJoinColumns = @JoinColumn ( name = "card_id" ) )
    @Fetch(FetchMode.JOIN)
    private Set<Card> cards = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "art_id")
    private ArtsCard art;

}
