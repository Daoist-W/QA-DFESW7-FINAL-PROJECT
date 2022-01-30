package com.qa.senpai.services;

import com.qa.senpai.data.dtos.UserDTO;
import com.qa.senpai.data.entities.User;
import com.qa.senpai.data.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {
    // Using mockito only for this unit test

    // Fields
    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper userMapper;

    @InjectMocks
    private UserService userService;

    private List<User> users;
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
        // when()
        // assertThat()
        // verify()
        fail("Implement me");
    }

    @Test
    void getById() {
        // TODO: test me
        // given
        // when()
        // assertThat()
        // verify()
        fail("Implement me");
    }

    @Test
    void getByName() {
        // TODO: test me
        // given
        // when()
        // assertThat()
        // verify()
        fail("Implement me");
    }

    @Test
    void getByDates() {
        // TODO: test me
        // given
        // when()
        // assertThat()
        // verify()
        fail("Implement me");
    }

    @Test
    void create() {
        // TODO: test me
        // given
        // when()
        // assertThat()
        // verify()
        fail("Implement me");
    }

    @Test
    void update() {
        // TODO: test me
        // given
        // when()
        // assertThat()
        // verify()
        fail("Implement me");
    }

    @Test
    void delete() {
        // TODO: test me
        // given
        // when()
        // assertThat()
        // verify()
        fail("Implement me");
    }
}
