package com.qa.senpai.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.senpai.data.dtos.UserDTO;
import com.qa.senpai.data.entities.User;
import com.qa.senpai.data.repositories.UserRepository;
import com.qa.senpai.data.support.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@AutoConfigureMockMvc // configure the MockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = { "classpath:schema.sql",
        "classpath:data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class UserControllerSystemIntegrationTest {
    // Fields
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    // Test variable set up;
    private List<User> usersInDatabase;
    private List<UserDTO> usersInDatabaseDTO;
    private Long nextNewElementsId;

    private User userToBeSaved;
    private UserDTO savedUserDTO;

    private User userToUpdate;
    private UserDTO updatedUserDTO;

    private UserDTO foundUserDTO;
    private UserDTO userToDeleteDTO;

    private User expectedUserWithId;
    private User expectedUserWithoutId;
    private UserDTO expectedUserWithIdDTO;
    private UserDTO expectedUserSavedDTO;
    private Object userFoundListDTO;
    private Long userId;

    UserControllerSystemIntegrationTest() {
    }


    @BeforeEach
    void setUp() { // runs before every test

        usersInDatabase = new ArrayList<>();
        usersInDatabase.addAll(userRepository.findAll());
        usersInDatabaseDTO = new ArrayList<>();
        for(User user : usersInDatabase) {
            usersInDatabaseDTO.add(new UserDTO(
                    user.getId(),
                    user.getPosition_(),
                    user.getForename(),
                    user.getSurname(),
                    user.getDob(),
                    user.getEmail(),
                    user.getPhoneNum(),
                    null
            ));
        }

        int size = usersInDatabase.size();
        nextNewElementsId = usersInDatabase.get(size - 1).getId() + 1;

        expectedUserWithId = new User(
                3L, Position.staff, "paris", "lorem",
                LocalDate.of(1991,9,15),
                "paris@youmail.com", "+4475649589", 79846545, null
        );

        expectedUserWithIdDTO = new UserDTO(
                3L, Position.staff, "paris", "lorem",
                LocalDate.of(1991,7,21),
                "paris@youmail.com", "+4475649589", null
        );

        expectedUserWithoutId = new User(
                Position.staff, "paris", "lorem",
                LocalDate.of(1991,9,15),
                "paris@youmail.com", "+4475649589", 79846545, null
        );

        userToBeSaved = new User(
                Position.staff, "Hercules", "Son of Zeus",
                LocalDate.of(1000,2,15),
                "Hercules@sonofgod.com", "+1", 123456789, null
        );

        expectedUserSavedDTO = new UserDTO(
                nextNewElementsId, Position.staff, "Hercules", "Son of Zeus",
                LocalDate.of(1000,2,15),
                "Hercules@sonofgod.com", "+1", null
        );

        userId = 3L;


        userToUpdate = new User(
                3L, Position.staff, "PARIS", "UPDATED",
                LocalDate.of(1991,9,18),
                "paris@youmail.com", "+4475649589", 11111, null
        );

        updatedUserDTO = new UserDTO(
                3L, Position.staff, "PARIS", "UPDATED",
                LocalDate.of(1991,9,18),
                "paris@youmail.com", "+4475649589", null
        );

        userFoundListDTO = List.of(usersInDatabaseDTO.get(0), usersInDatabaseDTO.get(1));

        userToDeleteDTO =  expectedUserWithIdDTO;

    }

    @Test
    void getAllUsersTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/user/admin");
        mockRequest.accept(MediaType.APPLICATION_JSON);

        // expected JSON results
        String users = objectMapper.writeValueAsString(usersInDatabaseDTO);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(users);

        mockMvc.perform(mockRequest)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher);
    }

    @Test
    void getUserByIdTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/user/admin/" + userId);
        mockRequest.accept(MediaType.APPLICATION_JSON);

        // expected JSON results
        String user = objectMapper.writeValueAsString(expectedUserWithIdDTO);

        // configure result matchers
        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(user);

        // assertion
        mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
    }

    @Test
    void getUsersByNameTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/user/admin/" + "don" + "/brand");
        mockRequest.accept(MediaType.APPLICATION_JSON);

        // expected JSON results
        String users = objectMapper.writeValueAsString(userFoundListDTO);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(users);

        mockMvc.perform(mockRequest)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher);

    }

    @Test
    void getUsersByDatesTest() {
        // expecting one or more objects matching dates submitted
        // TODO: test me
    }

    @Test
    void createUserTest() throws Exception {
        // expecting HTTP status 202 CREATED
        // expecting an object reflecting submitted data for confirmation
        // TODO: test me

        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/user/create");
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(userToBeSaved));
        mockRequest.accept(MediaType.APPLICATION_JSON);

        //Configure result matchers
        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedUserSavedDTO));

        // Send request and assert the results were as expected
        mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);

    }

    @Test
    void updateUserByIdTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.PUT, "/user/update/" + updatedUserDTO.getId());
        mockRequest.accept(MediaType.APPLICATION_JSON);
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(userToUpdate));

        // expected JSON results
        String users = objectMapper.writeValueAsString(updatedUserDTO);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(users);

        mockMvc.perform(mockRequest)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher);
    }

    @Test
    void deleteUserByIdTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.DELETE, "/user/admin/delete/" + userToDeleteDTO.getId());
        mockRequest.accept(MediaType.APPLICATION_JSON);

        // expected JSON results
        String user = objectMapper.writeValueAsString(userToDeleteDTO);

        // configure result matchers
        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(user);

        // assertion
        mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
    }


}
