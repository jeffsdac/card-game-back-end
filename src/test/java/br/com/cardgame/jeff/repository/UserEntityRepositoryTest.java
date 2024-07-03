package br.com.cardgame.jeff.repository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import br.com.cardgame.jeff.model.UserEntityCard;

@DataJpaTest
@ActiveProfiles("test")
public class UserEntityRepositoryTest {

    @Autowired
    private UserEntityRepository userRepo;

    @Test
    void contextLoads() {
    }

    @Test
    public void UserEntityRepository_SaveAll_ReturnSavedUserEntityRepository (){
        // Arrange
        var userTest = UserEntityCard.builder()
        .email("jeff@jeff.com.br")
        .username("jeffin157")
        .password("zikaDoPantano123")
        .fullname("Jefferson De Andrade")
        .build();

        // Act

        var savedUser = userRepo.save(userTest);

        //Assert
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
    

    @Test
    public void UserEntityRepository_GetAll_ReturnMoreThanOneUserEntity(){
        // Arrange
        var userTest1 = UserEntityCard.builder()
        .email("jeff@jeff.com.br")
        .username("jeffin157")
        .password("zikaDoPantano123")
        .fullname("Jefferson De Andrade")
        .build();

        var userTest2 = UserEntityCard.builder()
        .email("jeff1@jeff.com.br")
        .username("jeffsdac")
        .password("zikaDoPantano1123")
        .fullname("Jefferson De Andrade Chaves")
        .build();

        userRepo.save(userTest1);
        userRepo.save(userTest2);
        
        //Act
        var users = userRepo.findAll();

        //Assert
        Assertions.assertThat(users).isNotNull();
        Assertions.assertThat(users.size()).isGreaterThan(1);
    }

    @Test
    public void UserEntityCard_FindById_ReturnsOneUserEntityCard () {
        
        //Arrange
        var userSave = UserEntityCard.builder()
        .email("jeff@jeff.com.br")
        .username("jeffin157")
        .password("zikaDoPantano123")
        .fullname("Jefferson De Andrade")
        .build();

        // Act
        userSave = userRepo.save(userSave);
        var getUser = userRepo.findById(userSave.getId()).get();

        //Assert
        Assertions.assertThat(getUser).isNotNull();
    }
}
