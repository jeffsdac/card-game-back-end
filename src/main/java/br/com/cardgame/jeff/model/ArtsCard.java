package br.com.cardgame.jeff.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table( name = "T_DECK_ARTS" )
public class ArtsCard {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private int id;

    private String name;

    private String type;

    @OneToMany ( mappedBy = "art", cascade = CascadeType.ALL )
    private Set<Card> cards;

    @OneToOne ( mappedBy = "art" )
    private Deck deck;

    @Lob
    @Column(name = "image_binary")
    private byte[] imageData;


}
