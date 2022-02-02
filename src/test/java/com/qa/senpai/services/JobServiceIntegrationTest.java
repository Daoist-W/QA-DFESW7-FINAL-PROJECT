package com.qa.senpai.services;

import com.qa.senpai.data.dtos.JobDTO;
import com.qa.senpai.data.entities.Job;
import com.qa.senpai.data.repositories.JobRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class JobServiceIntegrationTest {
    // Using mockito only for this unit test

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


    @BeforeEach
    public void init() { // runs before every test
        List<Job> allJobs = List.of(
                new Job(1L,
                        "topjob",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 3, 4),
                        LocalDate.of(2022, 3, 4)
                ),
                new Job(2L,
                        "topjob",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 3, 4),
                        LocalDate.of(2022, 3, 4)
                ),
                new Job(3L,
                        "topjob3",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 2, 4),
                        LocalDate.of(2022, 2, 6)
                ),
                new Job(4L,
                        "topjob4",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 2, 4),
                        LocalDate.of(2022, 2, 12)
                )
        );
        jobsDatabase = jobRepository.saveAll(allJobs);

        jobsDTO = List.of(
                new JobDTO(1L,
                        "topjob",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 3, 4),
                        LocalDate.of(2022, 3, 4)
                ),
                new JobDTO(2L,
                        "topjob",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 3, 4),
                        LocalDate.of(2022, 3, 4)
                ),
                new JobDTO(3L,
                        "topjob3",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 2, 4),
                        LocalDate.of(2022, 2, 6)
                ),
                new JobDTO(4L,
                        "topjob4",
                        "best job in the world",
                        "London",
                        LocalDate.of(2022, 2, 4),
                        LocalDate.of(2022, 2, 12)
                )
        );

        jobToBeSaved = new Job(
                (allJobs.get(allJobs.size() - 1).getId() + 1), // find id of last element and add 1
                "new top job",
                "best job in the world",
                "London",
                LocalDate.of(2022, 2, 4),
                LocalDate.of(2022, 2, 12)
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
                LocalDate.of(2022, 2, 6)
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
    void getAll() {
        assertThat(jobService.getAll()).isEqualTo(jobsDTO);
    }

    @Test
    void getById() {
        assertThat(jobService.getById(jobId)).isEqualTo(foundJobDTO);
    }

    @Test
    void getByTitle() {
        assertThat(jobService.getByTitle("topjob"))
                .isEqualTo(listOfJobsByDateDTO);
    }

    @Test
    void getByDates() {
        assertThat(jobService.getByDates(jobStartDate, jobEndDate))
                .isEqualTo(listOfJobsByDateDTO);
    }

    @Test
    void create() {
        assertThat(jobService.create(jobToBeSaved)).isEqualTo(savedJobDTO);
    }

    @Test
    void update() {
        assertThat(jobService.update(jobId, jobToUpdate)).isEqualTo(updatedJobDTO);
    }

    @Test
    void delete() {
        System.out.println(jobRepository.findAll());
        assertThat(jobService.delete(jobId)).isEqualTo(jobToDeleteDTO);
    }

    @Test
    void deleteByTitle() {
        assertThat(jobService.deleteByTitle("topjob")).isEqualTo(listOfJobsByDateDTO);
    }
}
