package br.com.cardgame.jeff.Service;


import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.cardgame.jeff.dtos.RelDeckSaveAllDto;
import br.com.cardgame.jeff.model.ArtsCard;
import br.com.cardgame.jeff.model.Card;
import br.com.cardgame.jeff.model.Deck;
import br.com.cardgame.jeff.model.RelDeckCard;
import br.com.cardgame.jeff.model.UserEntityCard;
import br.com.cardgame.jeff.model.tipoArt.ArtType;
import br.com.cardgame.jeff.model.tipoArt.CardType;
import br.com.cardgame.jeff.repository.ArtsCardRepository;
import br.com.cardgame.jeff.repository.CardRepository;
import br.com.cardgame.jeff.repository.DeckRepository;
import br.com.cardgame.jeff.repository.UserEntityRepository;
import br.com.cardgame.jeff.service.RelDeckCardService;

@ActiveProfiles("test")
@SpringBootTest
public class RelDeckCardServiceTest {
    
    @Test
    public void isRuning(){
    };

    @Autowired
    RelDeckCardService relsService;

    @Autowired
    ArtsCardRepository artRepo;

    @Autowired
    UserEntityRepository userRepo;

    @Autowired
    DeckRepository deckRepo;

    @Autowired 
    CardRepository cardRepo;

    private ArtsCard getTemplateArt() {
        byte[] base64 = "iVBORw0KGgoAAAANSUhEUgAAAAMAAAATCAYAAABV/rckAAAABHNCSVQICAgIfAhkiAAAABl0RVh0U29mdHdhcmUAZ25vbWUtc2NyZWVuc2hvdO8Dvz4AAAAmdEVYdENyZWF0aW9uIFRpbWUAcXVhIDA3IGFnbyAyMDI0IDIwOjI5OjAzv59GMgAAABVJREFUCJlj5BeU+s8ABUwMSGBYcwA83wFfoxcdLAAAAABJRU5ErkJggg==".getBytes();

        var art = ArtsCard.builder()
        .name("myart.png")
        .tipoArt(ArtType.BACKGROUNDART)
        .type("png")
        .imageData(base64)
        .build();

        return art;
    };

    private Card getTemplateCard(){
        var art = artRepo.save(getTemplateArt());


        return Card.builder()
        .art(art)
        .attack(1)
        .cardType(CardType.NECRO)
        .description("LOREM")
        .healthPoints(2)
        .mana(1)
        .build();

    };

    private UserEntityCard getTemplateUser () {
        var userTest1 = UserEntityCard.builder()
        .email("jeff@jeff.com.br")
        .username("jeffin157")
        .password("zikaDoPantano123")
        .fullname("Jefferson De Andrade")
        .build();

        return userTest1;
    }

    private Deck getTemplateDeck () {
        var art = artRepo.save(getTemplateArt());
        var user = userRepo.save(getTemplateUser());
        
        return Deck.builder()
        .art(art)
        .user(user)
        .name("Deck legal")
        .build();
    }

    @Test
    @DisplayName("Should return a List of Rels")
    public void RelDeckCardoService_SaveAll_ShouldReturnListRels (){
        
        var deck = deckRepo.save(getTemplateDeck());
        var card1 = cardRepo.save(getTemplateCard());
        var card2 = cardRepo.save(getTemplateCard());

        var relsDto = new ArrayList<RelDeckSaveAllDto>();

        var dto1 = new RelDeckSaveAllDto(card1.getId(), deck.getId(), 2);
        relsDto.add(dto1);
        var dto2 = new RelDeckSaveAllDto(card2.getId(), deck.getId(), 2);
        relsDto.add(dto2);

        var rels = relsService.saveAll(relsDto);


        Assertions.assertThat(rels.size()).isEqualTo(2);
        Assertions.assertThat(rels).allMatch((r) -> r.getTimesRelacted() == 2);
        Assertions.assertThat(rels).allMatch((r) -> r.getDeck().getId() == deck.getId());


    }

    @Test
    @DisplayName("Should reduce one of rels qtd")
    public void RelDeckCardService_MinusOne_ShouldRemoveOneQtd () {
        var deck = deckRepo.save(getTemplateDeck());
        var card1 = cardRepo.save(getTemplateCard());
        var rel = relsService.saveRel(deck.getId(),card1.getId());

        var relBd = relsService.minusOneQtd(rel.getId());

        Assertions.assertThat(relBd).isNotNull();
        Assertions.assertThat(relBd.qtd()).isEqualTo(0);
    }
}
