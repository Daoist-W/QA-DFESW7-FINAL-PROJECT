package com.qa.senpai.controllers;

import com.qa.senpai.services.AvailabilityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.fail;

@WebMvcTest(AvailabilityController.class)
class AvailabilityControllerWebIntegrationTest {
    // This test is restricting the application context to only
    // the AvailabilityController, though some other components are initialised too
    // we will use mockito to set up a mock up of the service class

    @Autowired // field injection
    private AvailabilityController availabilityController;

    @MockBean
    private AvailabilityService availabilityService;

    // TODO: Add data for tests

    @BeforeEach
    void setUp() { // this runs before every test
        // TODO: implement me
        fail("Implement me");

    }

    @AfterEach
    void tearDown() { // this runs after every test
        // TODO: implement me
        // need a tear down to get around issue I was having
        fail("Implement me");

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
    void getAvailabilityByTitleTest() {
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
