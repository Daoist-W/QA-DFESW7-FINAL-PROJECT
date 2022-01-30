package com.qa.senpai.controllers;

import com.qa.senpai.services.JobService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.fail;

@WebMvcTest(JobController.class)
class JobControllerWebIntegrationTest {
    // This test is restricting the application context to only
    // the JobController, though some other components are initialised too
    // we will use mockito to set up a mock up of the service class

    @Autowired // field injection
    private JobController jobController;

    @MockBean
    private JobService jobService;

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
    void getAllJobsTest() {
        // expecting a list of Job objects
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void getJobByIdTest() {
        // expecting a single object matching id submitted
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void getJobsByTitleTest() {
        // expecting one or more objects matching name submitted
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void getJobsByDatesTest() {
        // expecting one or more objects matching dates submitted
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void createJobTest() {
        // expecting HTTP status 202 CREATED
        // expecting an object reflecting submitted data for confirmation
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void updateJobByIdTest() {
        // expecting HTTP status 200 OK
        // should return updated object for visual confirmation
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void deleteJobByIdTest() {
        // expecting HTTP status 200 OK
        // should return deleted object for visual confirmation
        // TODO: test me
        fail("Implement me");

    }

    @Test
    void deleteJobByTitleTest() {
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
