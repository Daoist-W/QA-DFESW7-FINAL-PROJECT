package com.qa.senpai.services;

import com.qa.senpai.data.dtos.JobDTO;
import com.qa.senpai.data.entities.Job;
import com.qa.senpai.data.repositories.JobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobService {

    // Fields
    private JobRepository jobRepository;
    private ModelMapper mapper; // allows for mapping DTO's to Entities

    @Autowired
    public JobService(JobRepository jobRepository, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.mapper = modelMapper;
    }


    // ##############################################
    //                   METHODS
    // ##############################################

    private JobDTO mapToDTO(Job job) { // maps DTO to Job object
        return this.mapper.map(job, JobDTO.class);
    }


    public List<JobDTO> getAll() {
        // TODO: implement me
        return null;
    }

    public JobDTO getById(Long id) {
        // TODO: implement me
        return null;
    }

    public List<JobDTO> getByTitle(String title) {
        // TODO: implement me
        return null;
    }

    public List<JobDTO> getByDates(LocalDate jobStartDate, LocalDate jobEndDate) {
        // TODO: Implement me
        return null;
    }


    public JobDTO create(Job job) {
        // TODO: implement me
        return null;
    }


    public JobDTO update(long id, Job job) {
        // TODO: implement me
        return null;
    }


    public JobDTO delete(Long id) {
        // TODO: implement me
        return null;
    }


}
