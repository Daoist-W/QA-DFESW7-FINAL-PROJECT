package com.qa.senpai.controllers;

import com.qa.senpai.data.dtos.AvailabilityDTO;
import com.qa.senpai.data.entities.Availability;
import com.qa.senpai.services.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/availability")
public class AvailabilityController {
    // Fields
    private final AvailabilityService availabilityService;

    @Autowired
    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }


    // ############################################
    //                  READ
    // ############################################

    @GetMapping
    public ResponseEntity<List<AvailabilityDTO>> getAllAvailability() {
        return ResponseEntity.ok(availabilityService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AvailabilityDTO> getAvailabilityById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        AvailabilityDTO availability = availabilityService.getById(id);
        headers.add("Location", "/availability/" + availability.getId());
        return new ResponseEntity<AvailabilityDTO>(availability, headers, HttpStatus.OK);
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
