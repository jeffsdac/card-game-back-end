package br.com.cardgame.jeff.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.cardgame.jeff.model.Card;

@DataJpaTest
@ActiveProfiles("test")
public class CardRepositoryTest {


    @Autowired
    CardRepository cardRepo;

    private Card getTemplateCard(){
        return null;
    }
}
