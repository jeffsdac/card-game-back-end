package br.com.cardgame.jeff.repository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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

    private UserEntityCard getTemplateUser () {
        return UserEntityCard.builder()
        .email("jeff@jeff.com.br")
        .username("jeffin157")
        .password("zikaDoPantano123")
        .fullname("Jefferson De Andrade")
        .build();
    }

    @Test
    public void UserEntityRepository_SaveAll_ReturnSavedUserEntityRepository (){
        // Arrange
        var userTest = getTemplateUser();
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
        var userSave = getTemplateUser();

        // Act
        userSave = userRepo.save(userSave);
        var getUser = userRepo.findById(userSave.getId()).get();

        //Assert
        Assertions.assertThat(getUser).isNotNull();
    }

    @Test
    @DisplayName("Should get user sucessfully from DB")
    void UserEntityCard_FindByUssernamer_ReturnsOneUser () {
        var user = getTemplateUser();

        userRepo.save(user);
        var userSave = userRepo.findByUsername("jeffin157").get();
        
        Assertions.assertThat(userSave).isNotNull();
        Assertions.assertThat(userSave.getEmail()).isEqualTo(user.getEmail());
        Assertions.assertThat(userSave.getFullname()).isEqualTo(user.getFullname());
        
    }

    @Test
    @DisplayName("Should checks if a user exists by email")
    void UserEntityCard_ExistsByEmail_ReturnsTrue () {
        
        var user = getTemplateUser();

        userRepo.save(user);
        var exists = userRepo.existsByEmail(user.getEmail());

        Assertions.assertThat(exists).isEqualTo(true);

    }

    @Test
    @DisplayName("Should checks if a user exists by email")
    void UserEntityCard_ExistsByEmail_ReturnsFalse () {
        
        var user = getTemplateUser();

        userRepo.save(user);
        var exists = userRepo.existsByEmail("Anything");

        Assertions.assertThat(exists).isEqualTo(false);

    }

    @Test
    @DisplayName("Should checks if a user exists by username")
    void UserEntityCard_ExistsByUsername_ReturnsTrue () {
        
        var user = getTemplateUser();

        userRepo.save(user);
        var exists = userRepo.existsByUsername(user.getUsername());

        Assertions.assertThat(exists).isEqualTo(true);

    }


    @Test
    @DisplayName("Should checks if a user exists by username")
    void UserEntityCard_ExistsByUsername_ReturnsFalse () {
        
        var user = getTemplateUser();

        userRepo.save(user);
        var exists = userRepo.existsByEmail("Anything");

        Assertions.assertThat(exists).isEqualTo(false);

    }
}
