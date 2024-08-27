package br.com.cardgame.jeff.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.cardgame.jeff.dtos.UserEntityUpdateDto;
import br.com.cardgame.jeff.model.UserEntityCard;
import br.com.cardgame.jeff.service.UserEntityService;

@ActiveProfiles("test")
@SpringBootTest
public class UserEntityServiceTest {

    @Autowired
    UserEntityService userServ;
    
    @Test
    public void isWorking(){
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

    @Test
    @DisplayName("Should return an uptade userEntity.")
    void UserEntityService_UpdateUser_ShouldReturnUserEntity(){
        
        var userTest = getTemplateUser();
        userTest = userServ.saveUserEntity(userTest);
        var dto = new UserEntityUpdateDto("jeffinlindo","novasenha",userTest.getUsername());
        var userTest2 = userServ.uptadeUser(dto);

        Assertions.assertThat(userTest.getUsername()).isNotEqualTo(userTest2.getUsername());
        Assertions.assertThat(userTest.getPassword()).isNotEqualTo(userTest2.getPassword());

    }

}
