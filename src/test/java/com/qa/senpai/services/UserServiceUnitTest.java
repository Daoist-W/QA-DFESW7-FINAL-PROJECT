package com.qa.senpai.services;

import com.qa.senpai.data.dtos.UserDTO;
import com.qa.senpai.data.entities.User;
import com.qa.senpai.data.repositories.UserRepository;
import com.qa.senpai.data.support.Position;
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

    private List<User> allUsers;
    private List<UserDTO> allUsersDTO;

    private Long userId;
    private User expectedUserWithId;
    private User expectedUserWithoutId;
    private UserDTO expectedUserWithIdDTO;

    private List<User> userFoundList;
    private List<UserDTO> userFoundListDTO;

    private User userToUpdate;
    private User updatedUser;
    private UserDTO updatedUserDTO;

    private User userToDelete;
    private UserDTO userToDeleteDTO;


    @BeforeEach
    public void init() { // runs before every test
        allUsers = new ArrayList<>();
        allUsersDTO = new ArrayList<>();
        allUsers.addAll(List.of(
                new User(
                        1L, Position.staff, "don", "brand",
                        LocalDate.of(1991,9,15),
                        "don@youmail.com", "+4475649589", "132156654"),
                new User(
                        2L, Position.staff, "don", "brand",
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

        userId = 3L;

        expectedUserWithId = new User(
                3L, Position.staff, "paris", "lorem",
                LocalDate.of(1991,9,15),
                "paris@youmail.com", "+4475649589", "79846545"
        );



        userToUpdate = new User(
                3L, Position.staff, "PARIS", "UPDATED",
                LocalDate.of(1991,9,18),
                "paris@youmail.com", "+4475649589", "11111"
        );

        updatedUser = new User(
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

        expectedUserWithoutId = new User(
                Position.staff, "paris", "lorem",
                LocalDate.of(1991,9,15),
                "paris@youmail.com", "+4475649589", "79846545"
        );

        userFoundList = List.of(allUsers.get(0), allUsers.get(1));
        userFoundListDTO = List.of(allUsersDTO.get(0), allUsersDTO.get(1));

        userToDelete = expectedUserWithId;
        userToDeleteDTO =  expectedUserWithIdDTO;


    }


    @Test
    void getAll() {
        when(userRepository.findAll()).thenReturn(allUsers);
        for(int i = 0; i < allUsers.size(); i++) {
            when(userMapper.map(allUsers.get(i), UserDTO.class))
                    .thenReturn(allUsersDTO.get(i));
        }
        // assertThat()
        assertThat(userService.getAll()).isEqualTo(allUsersDTO);
        // verify()
        verify(userRepository).findAll();
        for (User allUser : allUsers) {
            verify(userMapper).map(allUser, UserDTO.class);
        }
    }

    @Test
    void getById() {
        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.getById(userId)).thenReturn(expectedUserWithId);
        when(userMapper.map(expectedUserWithId, UserDTO.class))
                .thenReturn(expectedUserWithIdDTO);
        // assertThat()
        assertThat(userService.getById(userId)).isEqualTo(expectedUserWithIdDTO);
        // verify()
        verify(userRepository).existsById(userId);
        verify(userRepository).getById(userId);
        verify(userMapper).map(expectedUserWithId, UserDTO.class);
    }

    @Test
    void getByName() {
        when(userRepository
                .findByFullName(
                        userFoundList.get(0).getForename(),
                        userFoundList.get(0).getSurname()
                )).thenReturn(userFoundList);

        for(int i = 0; i < userFoundList.size(); i++) {
            when(userMapper.map(userFoundList.get(i), UserDTO.class))
                    .thenReturn(userFoundListDTO.get(i));
        }
        // assertThat()
        assertThat(userService.getByName(
                userFoundList.get(0).getForename(),
                userFoundList.get(0).getSurname()))
                .isEqualTo(userFoundListDTO);
        // verify()
        verify(userRepository).findByFullName(
                userFoundList.get(0).getForename(),
                userFoundList.get(0).getSurname());
        for (User user : userFoundList) {
            verify(userMapper).map(user, UserDTO.class);
        }
    }

    @Test
    void getByDates() {
        // TODO: test me
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
        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.getById(userId)).thenReturn(expectedUserWithId);
        when(userRepository.save(expectedUserWithId)).thenReturn(updatedUser);
        when(userMapper.map(updatedUser, UserDTO.class)).thenReturn(updatedUserDTO);
        // assertThat()
        assertThat(userService.update(userId, userToUpdate)).isEqualTo(updatedUserDTO);
        // verify()
        verify(userRepository).existsById(userId);
        verify(userRepository).getById(userId);
        verify(userRepository).save(expectedUserWithId);
        verify(userMapper).map(updatedUser, UserDTO.class);
    }

    @Test
    void delete() {
        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.getById(userId)).thenReturn(userToDelete);
        when(userMapper.map(userToDelete, UserDTO.class)).thenReturn(userToDeleteDTO);
        // assertThat()
        assertThat(userService.delete(userId)).isEqualTo(userToDeleteDTO);
        // verify()
        verify(userRepository).existsById(userId);
        verify(userRepository).getById(userId);
        verify(userRepository).deleteById(userId);
    }
}
