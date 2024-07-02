package br.com.cardgame.jeff.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Lob
    @Column(name = "image_binary", length = 204800) // 200Kb
    private byte[] imageData;
}
