package br.com.cardgame.jeff.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

import br.com.cardgame.jeff.model.tipoArt.ArtType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table( name = "T_DECK_ARTS" )
@Builder
public class ArtsCard {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ArtType tipoArt;

    private String type;

    @OneToMany ( mappedBy = "art", cascade = CascadeType.ALL )
    private Set<Card> cards;


    @OneToMany(mappedBy = "art", cascade = CascadeType.ALL)
    private Set<Deck> deck;

    @Lob
    @Column(name = "image_binary")
    private byte[] imageData;


}
