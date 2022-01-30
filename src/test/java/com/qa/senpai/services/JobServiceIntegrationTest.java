package com.qa.senpai.services;

import com.qa.senpai.data.dtos.JobDTO;
import com.qa.senpai.data.entities.Job;
import com.qa.senpai.data.repositories.JobRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Transactional
class JobServiceIntegrationTest {
    // Using mockito only for this unit test

    // Fields
    @Autowired
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    private List<Job> jobsInDatabase;
    private List<JobDTO> jobsDTO;


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
    void getByDates() {
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
