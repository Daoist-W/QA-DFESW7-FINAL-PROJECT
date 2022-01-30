package com.qa.senpai.services;

import com.qa.senpai.data.dtos.JobDTO;
import com.qa.senpai.data.entities.Job;
import com.qa.senpai.data.repositories.JobRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
class JobServiceUnitTest {
    // Using mockito only for this unit test

    // Fields
    @Mock
    private JobRepository jobRepository;

    @Mock
    private ModelMapper jobMapper;

    @InjectMocks
    private JobService jobService;

    private List<Job> jobs;
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
    void getByTitle() {
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
    void create() {
        // TODO: test me
        // given
        // when()
        // assertThat()
        // verify()
        fail("Implement me");
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
