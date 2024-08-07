package br.com.cardgame.jeff.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.cardgame.jeff.model.ArtsCard;
import br.com.cardgame.jeff.model.tipoArt.ArtType;

@DataJpaTest
@ActiveProfiles("test")
public class ArtsCardRepositoryTest {

    @Autowired
    ArtsCardRepository artsCardRepo;

    @Test
    void contextLoad(){}


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
    @DisplayName("Should return all BACKGROUNDART arts")
    void ArtsCard_FindByTypeArt_ReturnOneOrMoreArts () {
        var art = getTemplateArt();

        artsCardRepo.save(art);
        var arts = artsCardRepo.findByTipoArt(ArtType.BACKGROUNDART).get();

        Assertions.assertThat(arts).isNotEmpty();
        Assertions.assertThat(arts).allMatch( (a) -> a.getTipoArt().equals(ArtType.BACKGROUNDART));
    }
}
