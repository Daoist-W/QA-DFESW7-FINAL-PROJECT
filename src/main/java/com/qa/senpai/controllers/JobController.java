package com.qa.senpai.controllers;

import com.qa.senpai.data.dtos.JobDTO;
import com.qa.senpai.data.entities.Job;
import com.qa.senpai.data.support.DateList;
import com.qa.senpai.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/job")
public class JobController {
    // Fields
    private JobService jobService;

    @Autowired
    // TODO: Review your understanding of autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    // ############################################
    //                  READ
    // ############################################

    @GetMapping
    public List<ResponseEntity<JobDTO>> getAllJobs() {
        // TODO: implement me
        return null;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {
        // TODO: implement me
        return null;
    }

    @GetMapping(path = "/{title}")
    public List<ResponseEntity<JobDTO>> getJobsByTitle(@PathVariable String title) {
        // TODO: implement me
        return null;
    }


    @PostMapping(path = "/available-jobs") // using post so I can take advantage of the body
    public List<ResponseEntity<JobDTO>> getJobsByDates(@RequestBody DateList dates) {
        // DateList is a support class made to support reading dates from the
        // request body, but first I will try it with ArrayList and see if it works
        // TODO: implement access control
        // TODO: implement me
        return null;
    }

    // ############################################
    //                  CREATE
    // ############################################
    @PostMapping(path = "/admin/create")
    public ResponseEntity<JobDTO> createJob(@Valid @RequestBody Job job) {
        // TODO: implement access control
        // TODO: implement me
        return null;
    }

    // ############################################
    //                  UPDATE
    // ############################################
    @PutMapping(path = "/admin/{id}")
    public ResponseEntity<JobDTO> updateJobById(@PathVariable("id") Long id, @Valid @RequestBody Job job) {
        // TODO: implement access control
        // TODO: implement me
        isAuthorised(0L, ""); // if authorised, proceed, otherwise handle decline
        return null;
    }


    // ############################################
    //                  DELETE
    // ############################################

    @DeleteMapping(path = "/admin/{id}")
    public ResponseEntity<JobDTO> deleteJobById(@PathVariable("id") Long id) {
        // TODO: implement access control
        // TODO: implement me
        return null;
    }

    @DeleteMapping(path = "/staff/{id}")
    public List<ResponseEntity<JobDTO>> deleteJobByTitle(@PathVariable("name") String title) {
        // TODO: implement access control
        // TODO: implement me
        isAuthorised(0L, ""); // if authorised, proceed, otherwise handle decline
        return null;
    }

    // ############################################
    //             SUPPORTING METHODS
    // ############################################

    // this supporting method will be used to validate passwords
    public boolean isAuthorised(Long id, String password) {
        // TODO: implement me
        return true;
    }
}
