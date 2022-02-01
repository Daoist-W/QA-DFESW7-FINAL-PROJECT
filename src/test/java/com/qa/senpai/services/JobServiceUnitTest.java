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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
    private Job jobToBeSaved;
    private Job savedJob;
    private JobDTO savedJobDTO;
    private Job jobToUpdate;
    private Job updatedJob;
    private Long jobId;
    private JobDTO updatedJobDTO;
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

    }

    @AfterEach
    void tearDown() { // runs after every test
        // TODO: implement me

    }

    @Test
    void getAll() {
        // TODO: test me
        // when()
        when(jobRepository.findAll()).thenReturn(allJobs);
        when(allJobs
                .stream()
                .map(job -> jobMapper.map(job, JobDTO.class))
                .collect(Collectors.toList())).thenReturn(allJobsDTO);
        // assertThat()
        assertThat(jobService.getAll()).isEqualTo(allJobsDTO);
        // verify()
        verify(jobRepository).findAll();
        verify(jobMapper, times(allJobsDTO.size()));
    }

    @Test
    void getById() {
        // TODO: test me
        // given
        // when()
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
        // TODO: test me
        // given
        // when()
        when(jobRepository.findBytitle(jobToBeFound.getTitle())).thenReturn(jobFoundList);
        when(jobFoundList
                .stream()
                .map(job -> jobMapper.map(job, JobDTO.class))
                .collect(Collectors.toList())).thenReturn(jobFoundListDTO);
        // assertThat()
        assertThat(jobService.getByTitle(jobToBeFound.getTitle())).isEqualTo(jobFoundListDTO);
        // verify()
        verify(jobRepository).findBytitle(jobToBeFound.getTitle());
        verify(jobMapper, times(jobFoundListDTO.size()));
    }

    @Test
    void getByDates() {
        // TODO: test me
        // given
        // when()
        when(jobRepository.findByDates(jobStartDate, jobEndDate))
                .thenReturn(listOfJobsByDate);
        when(listOfJobsByDate
                .stream()
                .map(job -> jobMapper.map(job, JobDTO.class))
                .collect(Collectors.toList())).thenReturn(listOfJobsByDateDTO);
        // assertThat()
        assertThat(jobService.getByDates(jobStartDate, jobEndDate)).isEqualTo(listOfJobsByDateDTO);

        // verify()
        verify(jobRepository).findByDates(jobStartDate, jobEndDate);
        verify(jobMapper, times(listOfJobsByDateDTO.size()));
    }

    @Test
    void create() {
        // TODO: test me
        when(jobRepository.save(jobToBeSaved)).thenReturn(savedJob);
        when(jobMapper.map(savedJob, JobDTO.class)).thenReturn(savedJobDTO);
        // assertThat()
        assertThat(jobService.create(jobToBeSaved)).isEqualTo(savedJobDTO);
        // verify()
        verify(jobRepository).save(jobToBeSaved);
    }

    @Test
    void update() {
        // TODO: test me
        // given
        // when()
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(jobToUpdate));
        when(jobRepository.save(updatedJob)).thenReturn(updatedJob);
        when(jobMapper.map(updatedJob, JobDTO.class)).thenReturn(updatedJobDTO);
        // assertThat()
        assertThat(jobService.update(jobId, jobToUpdate)).isEqualTo(updatedJobDTO);
        // verify()
        verify(jobRepository).findById(jobId);
        verify(jobRepository).save(updatedJob);
        verify(jobMapper).map(updatedJob, JobDTO.class);
    }

    @Test
    void delete() {
        // TODO: test me
        // given
        // when()
        when(jobRepository.existsById(jobId)).thenReturn(true);
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(jobToDelete));
        when(jobMapper.map(jobToDelete, JobDTO.class)).thenReturn(updatedJobDTO);
        // assertThat()
        assertThat(jobService.delete(jobId)).isEqualTo(jobToDeleteDTO);
        // verify()
        verify(jobRepository).existsById(jobId);
        verify(jobRepository).findById(jobId);
    }
}
