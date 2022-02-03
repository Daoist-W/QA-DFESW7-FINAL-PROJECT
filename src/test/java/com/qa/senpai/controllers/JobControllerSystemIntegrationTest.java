package com.qa.senpai.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.senpai.data.dtos.JobDTO;
import com.qa.senpai.data.entities.Availability;
import com.qa.senpai.data.entities.Job;
import com.qa.senpai.data.repositories.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// @Transactional disabled due to configuration issues, replaced with DirtiesContext
@AutoConfigureMockMvc // configure the MockMvc
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = { "classpath:schema.sql",
        "classpath:data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class JobControllerSystemIntegrationTest {
    // TODO: https://zetcode.com/spring/mockmvc/#:~:text=MockMvc%20is%20defined%20as%20a,between%20unit%20and%20integration%20tests.

    // Fields
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JobRepository jobRepository;

    // Test variables set up
    private List<Job> jobsInDatabase;
    private List<JobDTO> jobsInDatabaseDTO;
    private Long newElementId; // this is for setUp implementation


    private Job jobToBeSaved;
    private JobDTO savedJobDTO;

    private Job jobToUpdate;
    private JobDTO updatedJobDTO;

    private JobDTO foundJobDTO;
    private JobDTO jobToDeleteDTO;

    private LocalDate jobStartDate;
    private LocalDate jobEndDate;
    private Availability dates;

    private List<JobDTO> listOfJobsByDateDTO;
    private Long userId;


    @BeforeEach
    public void init() { // runs before every test

        jobsInDatabase = jobRepository.findAll();
        int size = jobsInDatabase.size();
        newElementId = jobsInDatabase.get(size - 1).getId() + 1;
        jobsInDatabaseDTO = new ArrayList<>();
        for(Job job : jobsInDatabase) {
            jobsInDatabaseDTO.add(new JobDTO(
                    job.getId(),
                    job.getTitle(),
                    job.getDescription_(),
                    job.getLocation(),
                    job.getStartDate(),
                    job.getEndDate()
            ));
        }

        jobToBeSaved = new Job(
                "new top job",
                "best job in the world",
                "London",
                LocalDate.of(2022, 2, 4),
                LocalDate.of(2022, 2, 12),
                null
        );

        savedJobDTO = new JobDTO(
                5L,
                "new top job",
                "best job in the world",
                "London",
                LocalDate.of(2022, 2, 4),
                LocalDate.of(2022, 2, 12)
        );

        Long jobId = 3L;
        userId = 1L;

        foundJobDTO = new JobDTO(3L,
                "topjob3",
                "best job in the world",
                "London",
                LocalDate.of(2022, 2, 4),
                LocalDate.of(2022, 2, 6)
        );

        jobToUpdate = new Job(3L,
                "UPDATED",
                "best job in the UNIVERSE",
                "London",
                LocalDate.of(2022, 2, 4),
                LocalDate.of(2022, 2, 6),
                null
        );

        updatedJobDTO = new JobDTO(3L,
                "UPDATED",
                "best job in the UNIVERSE",
                "London",
                LocalDate.of(2022, 2, 4),
                LocalDate.of(2022, 2, 6)
        );

        jobToDeleteDTO = foundJobDTO;


        // BY DATE

        jobStartDate = LocalDate.of(2022, 3, 3);
        jobEndDate = LocalDate.of(2022, 3, 13);

        listOfJobsByDateDTO = List.of(jobsInDatabaseDTO.get(0), jobsInDatabaseDTO.get(1));

        dates = new Availability(jobStartDate, jobEndDate, null );





    }

    @Test
    void getAllJobsTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/jobs");
        mockRequest.accept(MediaType.APPLICATION_JSON);

        // expected JSON results
        String jobs = objectMapper.writeValueAsString(jobsInDatabaseDTO);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(jobs);

        mockMvc.perform(mockRequest)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher);
    }

    @Test
    void getJobByIdTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/jobs/" + foundJobDTO.getId());
        mockRequest.accept(MediaType.APPLICATION_JSON);

        // expected JSON results
        String job = objectMapper.writeValueAsString(foundJobDTO);

        // configure result matchers
        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(job);

        // assertion
        mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
    }

    @Test
    void getJobsByTitleTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/jobs/title/" + "topjob");
        mockRequest.accept(MediaType.APPLICATION_JSON);

        // expected JSON results
        String jobs = objectMapper.writeValueAsString(listOfJobsByDateDTO);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(jobs);

        mockMvc.perform(mockRequest)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher);

    }

    @Test
    void getJobsByDatesTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.POST, "/jobs/dates");
        mockRequest.accept(MediaType.APPLICATION_JSON);
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(dates));

        // expected JSON results
        String jobs = objectMapper.writeValueAsString(listOfJobsByDateDTO);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(jobs);

        mockMvc.perform(mockRequest)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher);

    }

    @Test
    void createJobTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.POST, "/jobs/admin/create");
        mockRequest.accept(MediaType.APPLICATION_JSON);
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(jobToBeSaved));

        // expected JSON results
        String jobs = objectMapper.writeValueAsString(savedJobDTO);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(jobs);

        mockMvc.perform(mockRequest)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher);


    }

    @Test
    void updateJobByIdTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.PUT, "/jobs/admin/" + updatedJobDTO.getId());
        mockRequest.accept(MediaType.APPLICATION_JSON);
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(jobToUpdate));

        // expected JSON results
        String jobs = objectMapper.writeValueAsString(updatedJobDTO);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(jobs);

        mockMvc.perform(mockRequest)
                .andExpect(statusMatcher)
                .andExpect(contentMatcher);
    }

    @Test
    void deleteJobByIdTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.DELETE, "/jobs/admin/delete/" + jobToDeleteDTO.getId());
        mockRequest.accept(MediaType.APPLICATION_JSON);

        // expected JSON results
        String job = objectMapper.writeValueAsString(foundJobDTO);

        // configure result matchers
        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(job);

        // assertion
        mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
    }

    @Test
    void deleteJobByTitleTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.DELETE, "/jobs/admin/delete/name/" + "topjob");
        mockRequest.accept(MediaType.APPLICATION_JSON);

        // expected JSON results
        String job = objectMapper.writeValueAsString(listOfJobsByDateDTO);

        // configure result matchers
        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(job);

        // assertion
        mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
    }

    @Test
    void getJobsByUserIdTest() throws Exception {
        // configure mock request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/jobs/userid/" + userId);
        mockRequest.accept(MediaType.APPLICATION_JSON);

        // expected JSON results
        String job = objectMapper.writeValueAsString(listOfJobsByDateDTO);

        // configure result matchers
        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(job);

        // assertion
        mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
    }

}
