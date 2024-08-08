package br.com.cardgame.jeff.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.cardgame.jeff.model.ArtsCard;
import br.com.cardgame.jeff.model.Card;
import br.com.cardgame.jeff.model.tipoArt.ArtType;
import br.com.cardgame.jeff.model.tipoArt.CardType;

@DataJpaTest
@ActiveProfiles("test")
public class CardRepositoryTest {

    @Test
    public void isRuning(){

    }


    @Autowired
    CardRepository cardRepo;

    @Autowired
    ArtsCardRepository artCardRepo;

    private Card getTemplateCard(){
        var art = artCardRepo.save(getTemplateArt());


        return Card.builder()
        .art(art)
        .attack(1)
        .cardType(CardType.NECRO)
        .description("LOREM")
        .healthPoints(2)
        .mana(1)
        .build();

    }

    private ArtsCard getTemplateArt() {
        byte[] base64 = "iVBORw0KGgoAAAANSUhEUgAAAAMAAAATCAYAAABV/rckAAAABHNCSVQICAgIfAhkiAAAABl0RVh0U29mdHdhcmUAZ25vbWUtc2NyZWVuc2hvdO8Dvz4AAAAmdEVYdENyZWF0aW9uIFRpbWUAcXVhIDA3IGFnbyAyMDI0IDIwOjI5OjAzv59GMgAAABVJREFUCJlj5BeU+s8ABUwMSGBYcwA83wFfoxcdLAAAAABJRU5ErkJggg==".getBytes();

        var art = ArtsCard.builder()
        .name("myart.png")
        .tipoArt(ArtType.BACKGROUNDART)
        .type("png")
        .imageData(base64)
        .build();

        return art;
    }

    @Test
    @DisplayName("Should return an array(listable) of ints")
    void Cards_FindAllJustId_ReturnArrayInt (){

        var card = getTemplateCard();
        
        cardRepo.save(card);
        var ids = cardRepo.findAllJustId();

        Assertions.assertThat(ids).isNotNull();
        Assertions.assertThat(ids.size()).isGreaterThan(0);
        Assertions.assertThat(ids).allMatch( (t) -> t == 1 );
    }


}
