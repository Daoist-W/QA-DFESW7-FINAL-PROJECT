package com.qa.senpai.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.senpai.data.entities.User;
import com.qa.senpai.data.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // configure the MockMvc
class UserControllerSystemIntegrationTest {
    // TODO: https://zetcode.com/spring/mockmvc/#:~:text=MockMvc%20is%20defined%20as%20a,between%20unit%20and%20integration%20tests.

    // Fields
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    private List<User> usersInDatabase;
    private Long newElementId; // this is for setUp implementation

    @BeforeEach
    void setUp() { // initialising data for tests
        // TODO: implement me
    }

    @AfterEach
    void tearDown() { // removing data from fields once test complete
        // TODO: implement me
        // only required if transactional still doesn't work
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
    void getUsersByNameTest() {
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
        fail("Implement me");

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
    void deleteUserByNameTest() {
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
