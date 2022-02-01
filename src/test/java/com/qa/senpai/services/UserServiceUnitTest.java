package com.qa.senpai.services;

import com.qa.senpai.data.dtos.UserDTO;
import com.qa.senpai.data.entities.User;
import com.qa.senpai.data.repositories.UserRepository;
import com.qa.senpai.data.support.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    private User expectedUserWithId;
    private User expectedUserWithoutId;
    private UserDTO expectedUserWithIdDTO;


    @BeforeEach
    public void init() { // runs before every test
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



    }

    @AfterEach
    public void tearDown() { // runs after every test
        users.clear();
        expectedUserWithId = null;
        expectedUserWithIdDTO = null;
        expectedUserWithoutId = null;

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
    void createUserTest() {
        when(userRepository.save(expectedUserWithoutId))
                .thenReturn(expectedUserWithId);
        when(this.userMapper.map(expectedUserWithId, UserDTO.class))
                .thenReturn(expectedUserWithIdDTO);

        assertThat(userService.create(expectedUserWithoutId)).isEqualTo(expectedUserWithIdDTO);
        verify(userRepository).save(expectedUserWithoutId);
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
