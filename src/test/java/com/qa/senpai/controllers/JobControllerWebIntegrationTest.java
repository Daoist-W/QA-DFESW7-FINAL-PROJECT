package com.qa.senpai.controllers;

import com.qa.senpai.data.dtos.JobDTO;
import com.qa.senpai.data.entities.Availability;
import com.qa.senpai.data.entities.Job;
import com.qa.senpai.services.JobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(JobController.class)
class JobControllerWebIntegrationTest {
    // This test is restricting the application context to only
    // the JobController, though some other components are initialised too
    // we will use mockito to set up a mock up of the service class

    @Autowired // field injection
    private JobController jobController;

    @MockBean
    private JobService jobService;

    // Test variable set up
    private List<Job> allJobs;
    private List<JobDTO> jobsDTO;

    private Job jobToBeSaved;
    private JobDTO savedJobDTO;

    private Job jobToUpdate;
    private JobDTO updatedJobDTO;

    private Long jobId;
    private JobDTO foundJobDTO;
    private JobDTO jobToDeleteDTO;

    private LocalDate jobStartDate;
    private LocalDate jobEndDate;

    private List<JobDTO> listOfJobsFoundDTO;
    private Availability dates;


    @BeforeEach
    public void init() { // runs before every test
        allJobs = List.of(
                new Job(1L,
                        "topjob",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 3, 4),
                        LocalDate.of(2022, 3, 4),
                        null
                ),
                new Job(2L,
                        "topjob",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 3, 4),
                        LocalDate.of(2022, 3, 4),
                        null
                ),
                new Job(3L,
                        "topjob3",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 2, 4),
                        LocalDate.of(2022, 2, 6),
                        null
                ),
                new Job(4L,
                        "topjob4",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 2, 4),
                        LocalDate.of(2022, 2, 12),
                        null
                )
        );

        jobsDTO = List.of(
                new JobDTO(1L,
                        "topjob",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 3, 4),
                        LocalDate.of(2022, 3, 4),
                        null
                ),
                new JobDTO(2L,
                        "topjob",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 3, 4),
                        LocalDate.of(2022, 3, 4),
                        null
                ),
                new JobDTO(3L,
                        "topjob3",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 2, 4),
                        LocalDate.of(2022, 2, 6),
                        null
                ),
                new JobDTO(4L,
                        "topjob4",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 2, 4),
                        LocalDate.of(2022, 2, 12),
                        null
                )
        );

        jobToBeSaved = new Job(
                (allJobs.get(allJobs.size() - 1).getId() + 1), // find id of last element and add 1
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
                LocalDate.of(2022, 2, 12),
                null
        );

        jobId = 3L;

        foundJobDTO = new JobDTO(3L,
                "topjob3",
                "best job in the world",
                "London",
                LocalDate.of(2022, 2, 4),
                LocalDate.of(2022, 2, 6),
                null
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
                LocalDate.of(2022, 2, 6),
                null
        );

        jobToDeleteDTO = foundJobDTO;


        // BY DATE

        jobStartDate = LocalDate.of(2022, 3, 3);
        jobEndDate = LocalDate.of(2022, 3, 13);

        dates = new Availability(jobStartDate, jobEndDate);

        listOfJobsFoundDTO = List.of(jobsDTO.get(0), jobsDTO.get(1));





    }


    @Test
    void getAllJobsTest() {
        when(jobService.getAll()).thenReturn(jobsDTO);
        assertThat(jobController.getAllJobs())
                .isEqualTo(ResponseEntity.ok(jobsDTO));
    }

    @Test
    void getJobByIdTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/jobs/" + jobId);
        when(jobService.getById(jobId))
                .thenReturn(foundJobDTO);
        assertThat(jobController.getJobById(jobId))
                .isEqualTo(new ResponseEntity<>(foundJobDTO, headers, HttpStatus.OK));

    }

    @Test
    void getJobsByTitleTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/jobs/title/" + "topjob");
        when(jobService.getByTitle("topjob"))
                .thenReturn(listOfJobsFoundDTO);
        assertThat(jobController.getJobsByTitle("topjob"))
                .isEqualTo(new ResponseEntity<>(listOfJobsFoundDTO, headers, HttpStatus.OK));

    }

    @Test
    void getJobsByDatesTest() {
        when(jobService.getByDates(jobStartDate, jobEndDate))
                .thenReturn(listOfJobsFoundDTO);
        assertThat(jobController.getJobsByDates(dates))
                .isEqualTo(ResponseEntity.ok(listOfJobsFoundDTO));
    }

    @Test
    void createJobTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/admin/create/" + savedJobDTO.getId());
        when(jobService.create(jobToBeSaved))
                .thenReturn(savedJobDTO);
        assertThat(jobController.createJob(jobToBeSaved))
                .isEqualTo(new ResponseEntity<>(savedJobDTO, headers, HttpStatus.CREATED));

    }

    @Test
    void updateJobByIdTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/admin/" + updatedJobDTO.getId());
        when(jobService.update(jobId, jobToUpdate))
                .thenReturn(updatedJobDTO);
        assertThat(jobController.updateJobById(jobId, jobToUpdate))
                .isEqualTo(new ResponseEntity<>(updatedJobDTO, headers, HttpStatus.OK));

    }

    @Test
    void deleteJobByIdTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/admin/delete/" + jobToDeleteDTO.getId());
        when(jobService.delete(jobId))
                .thenReturn(jobToDeleteDTO);
        assertThat(jobController.deleteJobById(jobId))
                .isEqualTo(new ResponseEntity<>(jobToDeleteDTO, headers, HttpStatus.OK));

    }

    @Test
    void deleteJobByTitleTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/admin/delete/" + "topjob");
        when(jobService.deleteByTitle("topjob"))
                .thenReturn(listOfJobsFoundDTO);
        assertThat(jobController.deleteJobByTitle("topjob"))
                .isEqualTo(new ResponseEntity<>(listOfJobsFoundDTO, headers, HttpStatus.OK));

    }


}
