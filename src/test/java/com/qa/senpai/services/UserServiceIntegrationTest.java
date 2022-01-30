package com.qa.senpai.services;

import com.qa.senpai.data.dtos.UserDTO;
import com.qa.senpai.data.entities.User;
import com.qa.senpai.data.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Transactional
class UserServiceIntegrationTest {
    // Using mockito only for this unit test

    // Fields
    @Autowired
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private List<User> usersInDatabase;
    private List<UserDTO> usersDTO;


    @BeforeEach
    void setUp() { // runs before every test
        // TODO: implement me

    }

    @AfterEach
    void tearDown() { // runs after every test
        // TODO: implement me

    }

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
    void create() {
        // TODO: test me
        // given
        // assertThat()
        fail("Implement me");
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
