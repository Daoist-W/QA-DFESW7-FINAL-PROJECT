package com.qa.senpai.controllers;

import com.qa.senpai.data.dtos.UserDTO;
import com.qa.senpai.data.entities.Job;
import com.qa.senpai.data.entities.User;
import com.qa.senpai.data.support.Position;
import com.qa.senpai.services.UserService;
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
import static org.mockito.Mockito.*;

@WebMvcTest(UserController.class)
class UserControllerWebIntegrationTest {
    // This test is restricting the application context to only
    // the web layer, though some other components are initialised too
    // we will use mockito to set up a mock up of the service class

    @Autowired
    // field injection
    // creates an instance with application context of Controller
    // and injects it into the test class as a field variable
    private UserController userController;

    @MockBean
    // mock instance within application context
    // this in turn will be injected into the controller
    private UserService userService;

    // Test variables set up
    private List<User> allUsers;
    private List<UserDTO> allUsersDTO;

    private User expectedUserWithId;
    private User expectedUserWithoutId;
    private List<UserDTO> userFoundListDTO;

    private User userToSave;
    private UserDTO expectedUserWithIdDTO;
    private UserDTO expectedUserSavedDTO;

    private User userToUpdate;
    private UserDTO updatedUserDTO;

    private Long userId;
    private UserDTO foundUserDTO;
    private UserDTO userToDeleteDTO;




    @BeforeEach
    void setUp() { // runs before every test

        allUsers = new ArrayList<>();
        allUsers.addAll(List.of(
                new User(
                        1L, Position.staff, "don", "brand",
                        LocalDate.of(1991,9,15),
                        "don@youmail.com", "+4475649589", 132156654, new ArrayList<Job>()),
                new User(
                        2L, Position.staff, "done", "brand",
                        LocalDate.of(1991,9,15),
                        "bob@youmail.com", "+4475649589", 123465, new ArrayList<Job>()),

                new User(
                        3L, Position.staff, "paris", "lorem",
                        LocalDate.of(1991,7,21),
                        "paris@youmail.com", "+4475649589", 79846545, new ArrayList<Job>()),

                new User(
                        4L, Position.admin, "don", "isiko",
                        LocalDate.of(1991,9,15),
                        "don@youmail.com", "+4475649589", 654821658, new ArrayList<Job>())

        ));

        allUsersDTO = new ArrayList<>();
        allUsersDTO.addAll(List.of(
                new UserDTO(
                        1L, Position.staff, "don", "brand",
                        LocalDate.of(1991,9,15),
                        "don@youmail.com", "+4475649589", new ArrayList<Job>()),
                new UserDTO(
                        2L, Position.staff, "don", "brand",
                        LocalDate.of(1991,9,15),
                        "harry@youmail.com", "+4475649589", new ArrayList<Job>()),

                new UserDTO(
                        3L, Position.staff, "paris", "lorem",
                        LocalDate.of(1991,7,21),
                        "paris@youmail.com", "+4475649589", new ArrayList<Job>()),

                new UserDTO(
                        4L, Position.admin, "don", "isiko",
                        LocalDate.of(1991,9,15),
                        "don@youmail.com", "+4475649589", new ArrayList<Job>())

        ));

        userId = 3L;


        userToUpdate = new User(
                3L, Position.staff, "PARIS", "UPDATED",
                LocalDate.of(1991,9,18),
                "paris@youmail.com", "+4475649589", 11111, new ArrayList<Job>()
        );

        updatedUserDTO = new UserDTO(
                3L, Position.staff, "PARIS", "UPDATED",
                LocalDate.of(1991,9,18),
                "paris@youmail.com", "+4475649589", new ArrayList<Job>()
        );

        expectedUserWithId = new User(
                3L, Position.staff, "paris", "lorem",
                LocalDate.of(1991,9,15),
                "paris@youmail.com", "+4475649589", 79846545, new ArrayList<Job>()
        );

        expectedUserWithIdDTO = new UserDTO(
                3L, Position.staff, "paris", "lorem",
                LocalDate.of(1991,7,21),
                "paris@youmail.com", "+4475649589", new ArrayList<Job>()
        );

        expectedUserWithoutId = new User(
                Position.staff, "paris", "lorem",
                LocalDate.of(1991,9,15),
                "paris@youmail.com", "+4475649589", 79846545, new ArrayList<Job>()
        );

        userToSave = new User(
                Position.staff, "Hercules", "Son of Zeus",
                LocalDate.of(1000,2,15),
                "Hercules@sonofgod.com", "+1", 123456789, new ArrayList<Job>()
        );

        expectedUserSavedDTO = new UserDTO(
                5L, Position.staff, "Hercules", "Son of Zeus",
                LocalDate.of(1000,2,15),
                "Hercules@sonofgod.com", "+1", new ArrayList<Job>()
        );

        userFoundListDTO = List.of(allUsersDTO.get(0), allUsersDTO.get(1));

        userToDeleteDTO =  expectedUserWithIdDTO;

    }


    @Test
    void getAllUsersTest() {
        when(userService.getAll()).thenReturn(allUsersDTO);
        assertThat(userController.getAllUsers())
                .isEqualTo(ResponseEntity.ok(allUsersDTO));

    }

    @Test
    void getUserByIdTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/user/admin/" + userId);
        when(userService.getById(userId))
                .thenReturn(foundUserDTO);
        assertThat(userController.getUserById(userId))
                .isEqualTo(new ResponseEntity<>(foundUserDTO, headers, HttpStatus.OK));
    }

    @Test
    void getUsersByNameTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/user/admin/" + "don" + "/brand");
        when(userService.getByName("don", "brand"))
                .thenReturn(userFoundListDTO);
        assertThat(userController.getUsersByName("don", "brand"))
                .isEqualTo(new ResponseEntity<>(userFoundListDTO, headers, HttpStatus.OK));

    }

    @Test
    void getUsersByDatesTest() {
        // expecting one or more objects matching dates submitted
        // TODO: test me
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
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/update/" + updatedUserDTO.getId());
        when(userService.update(userId, userToUpdate))
                .thenReturn(updatedUserDTO);
        assertThat(userController.updateUserById(userId, userToUpdate))
                .isEqualTo(new ResponseEntity<>(updatedUserDTO, headers, HttpStatus.OK));

    }

    @Test
    void deleteUserByIdTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/admin/delete/" + userToDeleteDTO.getId());
        when(userService.delete(userId))
                .thenReturn(userToDeleteDTO);
        assertThat(userController.deleteUserById(userId))
                .isEqualTo(new ResponseEntity<>(userToDeleteDTO, headers, HttpStatus.OK));
    }



}
