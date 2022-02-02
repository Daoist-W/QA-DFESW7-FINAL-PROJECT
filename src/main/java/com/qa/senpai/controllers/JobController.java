package com.qa.senpai.controllers;

import com.qa.senpai.data.dtos.JobDTO;
import com.qa.senpai.data.entities.Availability;
import com.qa.senpai.data.entities.Job;
import com.qa.senpai.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/jobs")
public class JobController {
    // Fields
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    // ############################################
    //                  READ
    // ############################################

    @GetMapping
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        JobDTO job = jobService.getById(id);
        headers.add("Location", "/jobs/" + job.getId());
        return new ResponseEntity<JobDTO>(job, headers, HttpStatus.OK);
    }

    @GetMapping(path = "/title/{title}")
    public ResponseEntity<List<JobDTO>> getJobsByTitle(@PathVariable String title) {
        List<JobDTO> jobs = jobService.getByTitle(title);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/jobs/title/" + title);
        return new ResponseEntity<List<JobDTO>>(jobs, headers, HttpStatus.OK);
    }


    @PostMapping(path = "/dates") // using post so I can take advantage of the body
    public ResponseEntity<List<JobDTO>> getJobsByDates(@RequestBody Availability dates) {
        LocalDate start = dates.getStartDate();
        LocalDate end = dates.getEndDate();
        List<JobDTO> jobs = jobService.getByDates(start, end);
        return ResponseEntity.ok(jobs);
    }

    // ############################################
    //                  CREATE
    // ############################################
    @PostMapping(path = "/admin/create")
    public ResponseEntity<JobDTO> createJob(@Valid @RequestBody Job job) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/admin/create/" + job.getId());
        return new ResponseEntity<>(jobService.create(job), headers, HttpStatus.CREATED);
    }

    // ############################################
    //                  UPDATE
    // ############################################
    @PutMapping(path = "/admin/{id}")
    public ResponseEntity<JobDTO> updateJobById(@PathVariable("id") Long id, @Valid @RequestBody Job job) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/admin/" + job.getId());
        return new ResponseEntity<>(jobService.update(id, job), headers, HttpStatus.OK);
    }


    // ############################################
    //                  DELETE
    // ############################################

    @DeleteMapping(path = "/admin/delete/{id}")
    public ResponseEntity<JobDTO> deleteJobById(@PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/admin/delete/" + id);
        return new ResponseEntity<>(jobService.delete(id), headers, HttpStatus.OK);
    }

    @DeleteMapping(path = "/admin/delete/name/{title}")
    public ResponseEntity<List<JobDTO>> deleteJobByTitle(@PathVariable("title") String title) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/admin/delete/" + title);
        return new ResponseEntity<>(jobService.deleteByTitle(title), headers, HttpStatus.OK);
    }

}
