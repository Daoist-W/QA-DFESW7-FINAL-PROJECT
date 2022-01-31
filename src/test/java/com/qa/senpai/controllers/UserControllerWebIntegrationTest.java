package com.qa.senpai.controllers;

import com.qa.senpai.data.dtos.UserDTO;
import com.qa.senpai.data.entities.User;
import com.qa.senpai.data.support.Position;
import com.qa.senpai.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@WebMvcTest(UserController.class)
class UserControllerWebIntegrationTest {
    // This test is restricting the application context to only
    // the UserController, though some other components are initialised too
    // we will use mockito to set up a mock up of the service class

    @Autowired // field injection
    private UserController userController;

    @MockBean
    private UserService userService;

    // TODO: Add data for tests
    // we need some data for our tests
    // set up a private list of users
    private List<User> users;
    private User validUser;
    private User userToSave;
    private UserDTO expectedUserSavedDTO;
    private User expectedUserWithId;
    private User expectedUserWithoutId;
    private UserDTO expectedUserWithIdDTO;

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

        expectedUserSavedDTO = new UserDTO(
                5L, Position.staff, "Hercules", "Son of Zeus",
                LocalDate.of(1000,2,15),
                "Hercules@sonofgod.com", "+1"
        );



    }

    @AfterEach
    void tearDown() { // runs after every test
        users.clear();
    }

    @Test
    void getAllUsersTest() {
        // expecting a list of User objects
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void getUserByIdTest() {
        // expecting a single object matching id submitted
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void getUsersByTitleTest() {
        // expecting one or more objects matching name submitted
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void getUsersByDatesTest() {
        // expecting one or more objects matching dates submitted
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void createUserTest() {
        // expecting HTTP status 202 CREATED
        // expecting an object reflecting submitted data for confirmation
        // TODO: test me
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/user/create/" + String.valueOf(expectedUserSavedDTO.getId()));
        ResponseEntity<UserDTO> expected = new ResponseEntity<>(expectedUserSavedDTO, headers, HttpStatus.CREATED);

        // when user to create is called, return valid user
        when((userService.create(userToSave))).thenReturn(expectedUserSavedDTO);

        // then
        ResponseEntity<UserDTO> actual = userController.createUser(userToSave);
        assertThat(expected).isEqualTo(actual);

        // verify number of calls (1 is default)
        verify(userService, times(1)).create(userToSave);

    }

    @Test
    void updateUserByIdTest() {
        // expecting HTTP status 200 OK
        // should return updated object for visual confirmation
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void deleteUserByIdTest() {
        // expecting HTTP status 200 OK
        // should return deleted object for visual confirmation
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void deleteUserByTitleTest() {
        // expecting HTTP status 200 OK
        // should return list of deleted objects for visual confirmation
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void isAuthorisedTest() {
        // should return true if submitted password matches current users password
        // TODO: test me
        fail("Implement me");

    }
}
