package br.com.cardgame.jeff.dtos;


// private int mana;
// private String description;
// private int ataque;
// private int defesa;

// @ManyToOne( fetch = FetchType.EAGER )
// @JoinColumn( name = "art_id", nullable = false )
// private ArtsCard art;

// @ManyToMany( mappedBy = "cards" )
// private Set<Deck> deck;

public record CardRegisterDto (int mana, String description, int ataque, int defesa, int artId, int deckId, String lore, String tittle) {
    
}
