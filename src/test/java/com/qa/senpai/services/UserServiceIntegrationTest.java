package com.qa.senpai.services;

import com.qa.senpai.data.dtos.UserDTO;
import com.qa.senpai.data.entities.User;
import com.qa.senpai.data.repositories.UserRepository;
import com.qa.senpai.data.support.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Transactional
class UserServiceIntegrationTest {
    // Using mockito only for this unit test

    // Fields
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private List<User> users;
    private List<User> usersInDatabase;
    private List<UserDTO> usersDTO;
    private User expectedUserWithId;
    private User userToSave;
    private User expectedUserWithoutId;
    private UserDTO expectedUserWithIdDTO;
    private UserDTO expectedUserSavedDTO;
    private Long nextNewElementsId;
    User userToSave2;



    @BeforeEach
    void setUp() { // runs before every test
        // TODO: implement me
        users = new ArrayList<>();
        users.addAll(List.of(
                new User(
                        1L, Position.staff, "don", "brand",
                        LocalDate.of(1991,9,15),
                        "don@youmail.com", "+4475649589", "132156654"),
                new User(
                        2L, Position.staff, "harry", "lerrt",
                        LocalDate.of(1991,9,15),
                        "harry@youmail.com", "+4475649589", "123465"),

                new User(
                        3L, Position.staff, "paris", "lorem",
                        LocalDate.of(1991,7,21),
                        "paris@youmail.com", "+4475649589", "79846545"),

                new User(
                        4L, Position.admin, "don", "isiko",
                        LocalDate.of(1991,9,15),
                        "don@youmail.com", "+4475649589", "654821658")

        ));

        usersInDatabase = new ArrayList<>();
        usersInDatabase.addAll(userRepository.saveAll(users));
        int size = usersInDatabase.size();
        nextNewElementsId = usersInDatabase.get(size - 1).getId() + 1;

        expectedUserWithId = new User(
                3L, Position.staff, "paris", "lorem",
                LocalDate.of(1991,9,15),
                "paris@youmail.com", "+4475649589", "79846545"
        );

        expectedUserWithIdDTO = new UserDTO(
                3L, Position.staff, "paris", "lorem",
                LocalDate.of(1991,7,21),
                "paris@youmail.com", "+4475649589"
        );

        expectedUserWithoutId = new User(
                Position.staff, "paris", "lorem",
                LocalDate.of(1991,9,15),
                "paris@youmail.com", "+4475649589", "79846545"
        );

        userToSave = new User(
                Position.staff, "Hercules", "Son of Zeus",
                LocalDate.of(1000,2,15),
                "Hercules@sonofgod.com", "+1", "123456789"
        );

        userToSave2 = new User(
                nextNewElementsId,
                Position.staff, "Hercules", "Son of Zeus",
                LocalDate.of(1000,2,15),
                "Hercules@sonofgod.com", "+1", "123456789"
        );

        expectedUserSavedDTO = new UserDTO(
                nextNewElementsId, Position.staff, "Hercules", "Son of Zeus",
                LocalDate.of(1000,2,15),
                "Hercules@sonofgod.com", "+1"
        );



    }

//    @AfterEach
//    void tearDown() { // runs after every test
//        users.clear();
//        usersInDatabase.clear();
//        nextNewElementsId = 0L;
//        userRepository.deleteAll();
//    }

    @Test
    void getAll() {
        // TODO: test me
        // assertThat()
        fail("Implement me");
    }

    @Test
    void getById() {
        // TODO: test me
        // given
        // assertThat()
        fail("Implement me");
    }

    @Test
    void getByName() {
        // TODO: test me
        // given
        // assertThat()
        fail("Implement me");
    }

    @Test
    void getByDates() {
        // TODO: test me
        // given
        // assertThat()
        fail("Implement me");
    }

    @Test
    void createUserTest() {
        assertThat(expectedUserSavedDTO)
                .isEqualTo(userService.create(userToSave2));
    }

    @Test
    void update() {
        // TODO: test me
        // given
        // assertThat()
        fail("Implement me");
    }

    @Test
    void delete() {
        // TODO: test me
        // given
        // assertThat()
        fail("Implement me");
    }
}
