package com.qa.senpai.services;

import com.qa.senpai.data.dtos.AvailabilityDTO;
import com.qa.senpai.data.entities.Availability;
import com.qa.senpai.data.repositories.AvailabilityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AvailabilityServiceIntegrationTest {
    // Using mockito only for this unit test

    // Fields
    @Autowired
    private AvailabilityRepository availabilityRepository;

    @InjectMocks
    private AvailabilityService availabilityService;

    private List<Availability> availabilityInDatabase;
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
        // assertThat()
        fail("Implement me");
    }

    @Test
    void getById() {
        // TODO: test me
        // given
        // assertThat()
        fail("Implement me");
    }

    @Test
    void getByTitle() {
        // TODO: test me
        // given
        // assertThat()
        fail("Implement me");
    }

    @Test
    void create() {
        // TODO: test me
        // given
        // assertThat()
        fail("Implement me");
    }

    @Test
    void update() {
        // TODO: test me
        // given
        // assertThat()
        fail("Implement me");
    }

    @Test
    void delete() {
        // TODO: test me
        // given
        // assertThat()
        fail("Implement me");
    }
}
