package br.com.cardgame.jeff.model;



import java.time.LocalDateTime;
import java.util.Set;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table( name = "T_CARD_DECK" )
@Builder
public class Deck {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    private LocalDateTime createdIn = LocalDateTime.now();

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntityCard user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "art_id")
    private ArtsCard art;


    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RelDeckCard> relDeckCard;

}
