package com.qa.senpai.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.senpai.data.dtos.UserDTO;
import com.qa.senpai.data.entities.User;
import com.qa.senpai.data.repositories.UserRepository;
import com.qa.senpai.data.support.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@Transactional
@AutoConfigureMockMvc // configure the MockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerSystemIntegrationTest {
    // Fields
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    private List<User> usersInDatabase;
    private List<User> users;
    private List<UserDTO> usersDTO;
    private User expectedUserWithId;
    private User userToSave;
    private User expectedUserWithoutId;
    private UserDTO expectedUserWithIdDTO;
    private UserDTO expectedUserSavedDTO;
    private Long nextNewElementsId;



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

        expectedUserSavedDTO = new UserDTO(
                nextNewElementsId, Position.staff, "Hercules", "Son of Zeus",
                LocalDate.of(1000,2,15),
                "Hercules@sonofgod.com", "+1"
        );



    }

    @AfterEach
    void tearDown() { // runs after every test
        users.clear();
        usersInDatabase.clear();
        nextNewElementsId = 0L;
        userRepository.deleteAll();
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
    void createUserTest() throws Exception {
        // expecting HTTP status 202 CREATED
        // expecting an object reflecting submitted data for confirmation
        // TODO: test me

        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/user/create");
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(userToSave));
        mockRequest.accept(MediaType.APPLICATION_JSON);

        //Configure result matchers
        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedUserSavedDTO));

        // Send request and assert the results were as expected
        mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);

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
