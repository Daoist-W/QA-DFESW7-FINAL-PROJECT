package com.qa.senpai.controllers;

import com.qa.senpai.data.dtos.AvailabilityDTO;
import com.qa.senpai.data.entities.Availability;
import com.qa.senpai.services.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/availability")
public class AvailabilityController {
    // Fields
    private AvailabilityService availabilityService;

    @Autowired
    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }


    // ############################################
    //                  READ
    // ############################################

    @GetMapping
    public List<ResponseEntity<AvailabilityDTO>> getAllAvailability() {
        // TODO: implement me
        return null;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AvailabilityDTO> getAvailabilityById(@PathVariable Long id) {
        // TODO: implement me
        return null;
    }

    @GetMapping(path = "/{title}")
    public List<ResponseEntity<AvailabilityDTO>> getAvailabilityByTitle(@PathVariable String title) {
        // TODO: implement me
        return null;
    }

    @GetMapping(path = "/{name}")
    public List<ResponseEntity<AvailabilityDTO>> getAvailabilityByName(@PathVariable String name) {
        // TODO: implement me
        return null;
    }


    // ############################################
    //                  CREATE
    // ############################################
    @PostMapping(path = "/create")
    public ResponseEntity<AvailabilityDTO> createAvailability(@Valid @RequestBody Availability availability) {
        // TODO: implement access control
        // TODO: implement me
        return null;
    }

    // ############################################
    //                  UPDATE
    // ############################################
    @PutMapping(path = "/{id}")
    public ResponseEntity<AvailabilityDTO> updateAvailabilityById(@PathVariable("id") Long id, @Valid @RequestBody Availability availability) {
        // TODO: implement access control
        // TODO: implement me
        return null;
    }


    // ############################################
    //                  DELETE
    // ############################################

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<AvailabilityDTO> deleteAvailabilityById(@PathVariable("id") Long id) {
        // TODO: implement access control
        // TODO: implement me
        return null;
    }

    // ############################################
    //             SUPPORTING METHODS
    // ############################################

}
