package com.qa.senpai.services;

import com.qa.senpai.data.dtos.AvailabilityDTO;
import com.qa.senpai.data.entities.Availability;
import com.qa.senpai.data.repositories.AvailabilityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class AvailabilityServiceUnitTest {
    // Using mockito only for this unit test

    // Fields
    @Mock
    private AvailabilityRepository availabilityRepository;

    @Mock
    private ModelMapper availabilityMapper;

    @InjectMocks
    private AvailabilityService availabilityService;

    private List<Availability> availability;
    private List<AvailabilityDTO> availabilityDTO;


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

    }

    @Test
    void getById() {
        // TODO: test me
        // given
        // when()
        // assertThat()
        // verify()

    }

    @Test
    void getByTitle() {
        // TODO: test me
        // given
        // when()
        // assertThat()
        // verify()

    }

    @Test
    void create() {
        // TODO: test me
        // given
        // when()
        // assertThat()
        // verify()

    }

    @Test
    void update() {
        // TODO: test me
        // given
        // when()
        // assertThat()
        // verify()

    }

    @Test
    void delete() {
        // TODO: test me
        // given
        // when()
        // assertThat()
        // verify()

    }
}
