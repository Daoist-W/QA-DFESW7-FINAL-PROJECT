package com.qa.senpai.services;

import com.qa.senpai.data.dtos.JobDTO;
import com.qa.senpai.data.entities.Job;
import com.qa.senpai.data.repositories.JobRepository;
import com.qa.senpai.exceptions.JobNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobService {

    // Fields
    private final JobRepository jobRepository;
    private final ModelMapper mapper; // allows for mapping DTO's to Entities

    @Autowired
    public JobService(JobRepository jobRepository, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.mapper = modelMapper;
    }


    // ##############################################
    //                   METHODS
    // ##############################################

    private JobDTO mapToDTO(Job job) { // maps DTO to Job object
        return mapper.map(job, JobDTO.class);
    }


    public List<JobDTO> getAll() {
        return jobRepository
                .findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public JobDTO getById(Long id) {
        if (jobRepository.existsById(id)) {
            return mapToDTO(jobRepository.getById(id));
        } else  {
            throw new JobNotFoundException("Job with id " + id + " not found");
        }

    }

    public List<JobDTO> getByTitle(String title) {
        return Optional.of(jobRepository
                .findBytitle(title)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList()))
                .orElseThrow(() -> new JobNotFoundException("Jobs with title " + title + " not found"));
    }

    public List<JobDTO> getByDates(LocalDate jobStartDate, LocalDate jobEndDate) {
        return Optional.of(jobRepository
                .findByDates(jobStartDate, jobEndDate)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList()))
                .orElseThrow(() -> new JobNotFoundException("Jobs with dates between " +
                        jobStartDate + " and " + jobEndDate + " not found"));
    }


    public JobDTO create(Job job) {
        return mapToDTO(jobRepository.save(job));
    }


    public JobDTO update(long id, Job job) {
        if(jobRepository.existsById(id)) {
            Job toUpdate = jobRepository.getById(id);
            toUpdate.setTitle(job.getTitle());
            toUpdate.setDescription_(job.getDescription_());
            toUpdate.setLocation(job.getLocation());
            toUpdate.setStartDate(job.getStartDate());
            toUpdate.setEndDate(job.getEndDate());
            Job save = jobRepository.save(toUpdate);
            return mapToDTO(save);
        } else {
            throw new JobNotFoundException("Job with id " + id + " not found");
        }
    }


    public JobDTO delete(Long id) {
        if(jobRepository.existsById(id)) {
            Job toDelete = jobRepository.getById(id);
            jobRepository.deleteById(id);
            return mapToDTO(toDelete);
        } else {
            throw new JobNotFoundException("Job with id " + id + " not found");
        }
    }

    public List<JobDTO> deleteByTitle(String title) {
        List<JobDTO> jobsToDelete = getByTitle(title);
        return Optional.of(jobsToDelete
                .stream()
                .map(jobDTO -> delete(jobDTO.getId()))
                .collect(Collectors.toList()))
                .orElseThrow(() -> new JobNotFoundException("Job with title " + title + " not found"));
    }


}
