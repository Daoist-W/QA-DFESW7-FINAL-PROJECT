package com.qa.senpai.services;

import com.qa.senpai.data.dtos.UserDTO;
import com.qa.senpai.data.entities.User;
import com.qa.senpai.data.repositories.UserRepository;
import com.qa.senpai.data.support.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Sql(scripts = { "classpath:schema.sql", // runs scripts for pre-populating tables before each test
        "classpath:data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class UserServiceIntegrationTest {
    // Using springBootTest for unit integration testing
    // we need the Repository layer & Service layer to be in the application context
    // and fully functional

    // Fields
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private List<UserDTO> usersInDatabaseDTO;

    private User userToSave;
    private UserDTO expectedUserWithIdDTO;
    private UserDTO expectedUserSavedDTO;
    private List<UserDTO> userFoundListDTO;
    private UserDTO userToDeleteDTO;
    private UserDTO updatedUserDTO;
    private Long userId;
    private User updatedUser;
    private User userToUpdate;


    @BeforeEach
    @Sql(scripts = { "classpath:schema.sql",
            "classpath:data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void setUp() { // runs before every test
        List<User> usersInDatabase = new ArrayList<>(userRepository.findAll());

        usersInDatabaseDTO = new ArrayList<>();
        for(User user : usersInDatabase) {
            usersInDatabaseDTO.add(new UserDTO(
                    user.getId(),
                    user.getPosition_(),
                    user.getForename(),
                    user.getSurname(),
                    user.getDob(),
                    user.getEmail(),
                    user.getPhoneNum()
            ));
        }

        int size = usersInDatabase.size();
        Long nextNewElementsId = usersInDatabase.get(size - 1).getId() + 1;

        userId = 3L;


        userToUpdate = new User(
                3L, Position.staff, "PARIS", "UPDATED",
                LocalDate.of(1991,9,18),
                "paris@youmail.com", "+4475649589", "11111"
        );

        updatedUserDTO = new UserDTO(
                3L, Position.staff, "PARIS", "UPDATED",
                LocalDate.of(1991,9,18),
                "paris@youmail.com", "+4475649589"
        );

        expectedUserWithIdDTO = new UserDTO(
                3L, Position.staff, "paris", "lorem",
                LocalDate.of(1991,7,21),
                "paris@youmail.com", "+4475649589"
        );

        userToSave = new User(
                Position.staff, "Hercules", "Son of Zeus",
                LocalDate.of(1000,2,15),
                "Hercules@sonofgod.com", "+1", "123456789"
        );

        expectedUserSavedDTO = new UserDTO(
                nextNewElementsId, Position.staff, "Hercules", "Son of Zeus",
                LocalDate.of(1000,2,15),
                "Hercules@sonofgod.com", "+1"
        );

        userFoundListDTO = List.of(this.usersInDatabaseDTO.get(0), this.usersInDatabaseDTO.get(1));

        userToDeleteDTO =  expectedUserWithIdDTO;



    }


    @Test
    void getAll() {
        assertThat(userService.getAll()).isEqualTo(usersInDatabaseDTO);
    }

    @Test
    void getById() {
        assertThat(userService.getById(userId)).isEqualTo(expectedUserWithIdDTO);
    }

    @Test
    void getByName() {
        assertThat(userService.getByName("don", "brand"))
                .isEqualTo(userFoundListDTO);
    }

    @Test
    void getByDates() {
        // TODO: test me
    }

    @Test
    void createUserTest() {
        assertThat(expectedUserSavedDTO)
                .isEqualTo(userService.create(userToSave));
    }

    @Test
    void update() {
        assertThat(userService.update(userId, userToUpdate)).isEqualTo(updatedUserDTO);
    }

    @Test
    void delete() {
        assertThat(userService.delete(userId)).isEqualTo(userToDeleteDTO);
    }
}
