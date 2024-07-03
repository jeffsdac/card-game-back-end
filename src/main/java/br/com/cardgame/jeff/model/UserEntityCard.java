package br.com.cardgame.jeff.model;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table( name = "T_CARD_USER" )
public class UserEntityCard {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    @NotEmpty
    private String email;
    
    @NotEmpty
    private String password;

    @NotEmpty
    private String fullname;

    @NotEmpty
    private String username;

    @OneToMany( mappedBy = "user", cascade = CascadeType.ALL )
    private List<Deck> decks;

}
