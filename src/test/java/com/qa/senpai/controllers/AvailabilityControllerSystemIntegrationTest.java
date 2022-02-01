package com.qa.senpai.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.senpai.data.entities.Availability;
import com.qa.senpai.data.repositories.AvailabilityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@Transactional
@AutoConfigureMockMvc // configure the MockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AvailabilityControllerSystemIntegrationTest {

    // Fields
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    private List<Availability> availabilityInDatabase;
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
    void getAllAvailabilityTest() {
        // expecting a list of Availability objects
        // TODO: test me
        fail("Implement me");
    }

    @Test
    void getAvailabilityByIdTest() {
        // expecting a single object matching id submitted
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void getAvailabilitysByTitleTest() {
        // expecting one or more objects matching name submitted
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void getAvailabilityByDatesTest() {
        // expecting one or more objects matching dates submitted
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void createAvailabilityTest() {
        // expecting HTTP status 202 CREATED
        // expecting an object reflecting submitted data for confirmation
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void updateAvailabilityByIdTest() {
        // expecting HTTP status 200 OK
        // should return updated object for visual confirmation
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void deleteAvailabilityByIdTest() {
        // expecting HTTP status 200 OK
        // should return deleted object for visual confirmation
        // TODO: test me
        fail("Implement me");
    }

    @Test
    void deleteAvailabilityByTitleTest() {
        // expecting HTTP status 200 OK
        // should return list of deleted objects for visual confirmation
        // TODO: test me
        fail("Implement me");
    }

}
