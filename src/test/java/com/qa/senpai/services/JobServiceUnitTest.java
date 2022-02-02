package com.qa.senpai.services;

import com.qa.senpai.data.dtos.JobDTO;
import com.qa.senpai.data.entities.Job;
import com.qa.senpai.data.repositories.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JobServiceUnitTest {
    // Using mockito only for this unit test

    // Fields
    @Mock
    private JobRepository jobRepository;

    @Mock
    private ModelMapper jobMapper;

    @InjectMocks
    private JobService jobService;

    private Job jobToBeSaved;
    private Job savedJob;
    private JobDTO savedJobDTO;
    private Job jobToUpdate;
    private Job updatedJob;
    private JobDTO updatedJobDTO;
    private Long jobId;
    private Job jobToDelete;
    private JobDTO jobToDeleteDTO;
    private LocalDate jobStartDate;
    private LocalDate jobEndDate;
    private List<Job> listOfJobsByDate;
    private List<JobDTO> listOfJobsByDateDTO;
    private Job jobToBeFound;
    private List<Job> jobFoundList;
    private List<JobDTO> jobFoundListDTO;
    private JobDTO jobToBeFoundDTO;
    private List<Job> allJobs;
    private List<JobDTO> allJobsDTO;


    @BeforeEach
    void setUp() { // runs before every test
        // TODO: implement me
        // READ
        allJobs = List.of(
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

        allJobsDTO = List.of(
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

        jobToBeFound = allJobs.get(2);
        jobToBeFoundDTO = allJobsDTO.get(2);


        // update
        jobToUpdate = jobToBeFound;
        updatedJob = new Job(3L,
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
        jobId = 3L;

        // DELETE
        jobToDelete = jobToBeFound;
        jobToDeleteDTO = new JobDTO(3L,
                "UPDATED",
                "best job in the UNIVERSE",
                "London",
                LocalDate.of(2022, 2, 4),
                LocalDate.of(2022, 2, 6)
        );

        // CREATE
        jobToBeSaved = new Job(
                (allJobs.get(allJobs.size() - 1).getId() + 1), // find id of last element and add 1
                "new top job",
                "best job in the world",
                "London",
                LocalDate.of(2022, 2, 4),
                LocalDate.of(2022, 2, 12)
        );

        savedJob = new Job(
                5L,
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

        // BY DATE

        jobStartDate = LocalDate.of(2022, 2, 3);
        jobEndDate = LocalDate.of(2022, 2, 13);

        listOfJobsByDate = List.of(allJobs.get(0), allJobs.get(1));
        listOfJobsByDateDTO = List.of(allJobsDTO.get(0), allJobsDTO.get(1));

        // BY TITLE

        jobFoundList = listOfJobsByDate;
        jobFoundListDTO = listOfJobsByDateDTO;








    }


    @Test
    void getAll() {
        when(jobRepository.findAll()).thenReturn(allJobs);
        for(int i = 0; i < allJobs.size(); i++) {
            when(jobMapper.map(allJobs.get(i), JobDTO.class))
                    .thenReturn(allJobsDTO.get(i));
        }
        // assertThat()
        assertThat(jobService.getAll()).isEqualTo(allJobsDTO);
        // verify()
        verify(jobRepository).findAll();
        for (Job allJob : allJobs) {
            verify(jobMapper).map(allJob, JobDTO.class);
        }
    }

    @Test
    void getById() {
        when(jobRepository.existsById(jobId)).thenReturn(true);
        when(jobRepository.getById(jobId)).thenReturn(jobToBeFound);
        when(jobMapper.map(jobToBeFound, JobDTO.class)).thenReturn(jobToBeFoundDTO);
        // assertThat()
        assertThat(jobService.getById(jobId)).isEqualTo(jobToBeFoundDTO);
        // verify()
        verify(jobRepository).existsById(jobId);
        verify(jobRepository).getById(jobId);
        verify(jobMapper).map(jobToBeFound, JobDTO.class);
    }

    @Test
    void getByTitle() {
        when(jobRepository.findBytitle(jobToBeFound.getTitle()))
                .thenReturn(jobFoundList);

        for(int i = 0; i < jobFoundList.size(); i++) {
            when(jobMapper.map(jobFoundList.get(i), JobDTO.class))
                    .thenReturn(jobFoundListDTO.get(i));
        }
        // assertThat()
        assertThat(jobService.getByTitle(jobToBeFound.getTitle()))
                .isEqualTo(jobFoundListDTO);
        // verify()
        verify(jobRepository).findBytitle(jobToBeFound.getTitle());
        for (Job job : jobFoundList) {
            verify(jobMapper).map(job, JobDTO.class);
        }
    }

    @Test
    void getByDates() {
        when(jobRepository.findByDates(jobStartDate, jobEndDate))
                .thenReturn(listOfJobsByDate);

        for(int i = 0; i < listOfJobsByDate.size(); i++) {
            when(jobMapper.map(listOfJobsByDate.get(i), JobDTO.class))
                    .thenReturn(listOfJobsByDateDTO.get(i));
        }
        // assertThat()
        assertThat(jobService.getByDates(jobStartDate, jobEndDate)).isEqualTo(listOfJobsByDateDTO);

        // verify()
        verify(jobRepository).findByDates(jobStartDate, jobEndDate);
        for (Job job : listOfJobsByDate) {
            verify(jobMapper).map(job, JobDTO.class);
        }
    }

    @Test
    void create() {
        when(jobRepository.save(jobToBeSaved)).thenReturn(savedJob);
        when(jobMapper.map(savedJob, JobDTO.class)).thenReturn(savedJobDTO);
        // assertThat()
        assertThat(jobService.create(jobToBeSaved)).isEqualTo(savedJobDTO);
        // verify()
        verify(jobRepository).save(jobToBeSaved);
    }

    @Test
    void update() {
        when(jobRepository.existsById(jobId)).thenReturn(true);
        when(jobRepository.getById(jobId)).thenReturn(jobToUpdate);
        when(jobRepository.save(jobToUpdate)).thenReturn(updatedJob);
        when(jobMapper.map(updatedJob, JobDTO.class)).thenReturn(updatedJobDTO);
        // assertThat()
        assertThat(jobService.update(jobId, jobToUpdate)).isEqualTo(updatedJobDTO);
        // verify()
        verify(jobRepository).existsById(jobId);
        verify(jobRepository).getById(jobId);
        verify(jobRepository).save(jobToUpdate);
        verify(jobMapper).map(updatedJob, JobDTO.class);
    }

    @Test
    void delete() {
        when(jobRepository.existsById(jobId)).thenReturn(true);
        when(jobRepository.getById(jobId)).thenReturn(jobToDelete);
        when(jobMapper.map(jobToDelete, JobDTO.class)).thenReturn(jobToDeleteDTO);
        // assertThat()
        assertThat(jobService.delete(jobId)).isEqualTo(jobToDeleteDTO);
        // verify()
        verify(jobRepository).existsById(jobId);
        verify(jobRepository).getById(jobId);
        verify(jobRepository).deleteById(jobId);
    }
}
