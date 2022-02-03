package com.qa.senpai.services;

import com.qa.senpai.data.dtos.JobDTO;
import com.qa.senpai.data.entities.Job;
import com.qa.senpai.data.repositories.JobRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Sql(scripts = { "classpath:schema.sql",
        "classpath:data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class JobServiceIntegrationTest {
    // Using springBootTest for unit integration testing
    // we need the Repository layer to be in the application context
    // and fully functional

    // Fields
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobService jobService;

    private List<Job> jobsDatabase;
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

    private List<JobDTO> listOfJobsByDateDTO;
    private Long userId;


    @BeforeEach
    public void init() { // runs before every test

        jobsDatabase = jobRepository.findAll();
        jobsDTO = new ArrayList<>();
        for(Job job : jobsDatabase) {
            jobsDTO.add(new JobDTO(
                    job.getId(),
                    job.getTitle(),
                    job.getDescription_(),
                    job.getLocation(),
                    job.getStartDate(),
                    job.getEndDate()
            ));
        }

        jobToBeSaved = new Job(
                (jobsDatabase.get(jobsDatabase.size() - 1).getId() + 1), // find id of last element and add 1
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

        jobId = 3L;
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

        listOfJobsByDateDTO = List.of(jobsDTO.get(0), jobsDTO.get(1));





    }

    @AfterEach
    public void tearDown() { // runs after every test
        jobsDatabase.clear();
        jobRepository.deleteAll();


    }

    @Test
    void getAllTest() {
        assertThat(jobService.getAll()).isEqualTo(jobsDTO);
    }

    @Test
    void getByIdTest() {
        assertThat(jobService.getById(jobId)).isEqualTo(foundJobDTO);
    }

    @Test
    void getByUserIdTest() {
        assertThat(jobService.getByUserId(userId)).isEqualTo(listOfJobsByDateDTO);
    }

    @Test
    void getByTitleTest() {
        assertThat(jobService.getByTitle("topjob"))
                .isEqualTo(listOfJobsByDateDTO);
    }

    @Test
    void getByDatesTest() {
        assertThat(jobService.getByDates(jobStartDate, jobEndDate))
                .isEqualTo(listOfJobsByDateDTO);
    }

    @Test
    void createTest() {
        assertThat(jobService.create(jobToBeSaved)).isEqualTo(savedJobDTO);
    }

    @Test
    void updateTest() {
        assertThat(jobService.update(jobId, jobToUpdate)).isEqualTo(updatedJobDTO);
    }

    @Test
    void deleteTest() {
        System.out.println(jobRepository.findAll());
        assertThat(jobService.delete(jobId)).isEqualTo(jobToDeleteDTO);
    }

    @Test
    void deleteByTitleTest() {
        assertThat(jobService.deleteByTitle("topjob")).isEqualTo(listOfJobsByDateDTO);
    }
}
