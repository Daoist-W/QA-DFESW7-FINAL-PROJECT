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

@SpringBootTest
@Transactional
class UserServiceIntegrationTest {
    // Using mockito only for this unit test

    // Fields
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private List<UserDTO> allUsersDTO;

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
    void setUp() { // runs before every test
        // TODO: implement me
        List<User> allUsers = new ArrayList<>(List.of(
                new User(
                        1L, Position.staff, "don", "brand",
                        LocalDate.of(1991, 9, 15),
                        "don@youmail.com", "+4475649589", "132156654"),
                new User(
                        2L, Position.staff, "don", "brand",
                        LocalDate.of(1991, 9, 15),
                        "harry@youmail.com", "+4475649589", "123465"),

                new User(
                        3L, Position.staff, "paris", "lorem",
                        LocalDate.of(1991, 7, 21),
                        "paris@youmail.com", "+4475649589", "79846545"),

                new User(
                        4L, Position.admin, "don", "isiko",
                        LocalDate.of(1991, 9, 15),
                        "don@youmail.com", "+4475649589", "654821658")

        ));

        allUsersDTO = new ArrayList<>();
        allUsersDTO.addAll(List.of(
                new UserDTO(
                        1L, Position.staff, "don", "brand",
                        LocalDate.of(1991,9,15),
                        "don@youmail.com", "+4475649589"),
                new UserDTO(
                        2L, Position.staff, "don", "brand",
                        LocalDate.of(1991,9,15),
                        "harry@youmail.com", "+4475649589"),

                new UserDTO(
                        3L, Position.staff, "paris", "lorem",
                        LocalDate.of(1991,7,21),
                        "paris@youmail.com", "+4475649589"),

                new UserDTO(
                        4L, Position.admin, "don", "isiko",
                        LocalDate.of(1991,9,15),
                        "don@youmail.com", "+4475649589")

        ));

        List<User> usersInDatabase = new ArrayList<>();
        usersInDatabase.addAll(userRepository.saveAll(allUsers));
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

        userFoundListDTO = List.of(allUsersDTO.get(0), allUsersDTO.get(1));

        userToDeleteDTO =  expectedUserWithIdDTO;



    }


    @Test
    void getAll() {
        assertThat(userService.getAll()).isEqualTo(allUsersDTO);
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
        System.out.println(userRepository.findAll());
        assertThat(userService.delete(userId)).isEqualTo(userToDeleteDTO);
    }
}
