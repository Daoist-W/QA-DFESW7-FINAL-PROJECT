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
import java.time.LocalDate;
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

    @PostMapping(path = "/dates") // using post so I can take advantage of the body
    public ResponseEntity<List<AvailabilityDTO>> getAvailabilitiesByDates(@RequestBody Availability dates) {
        LocalDate start = dates.getStartDate();
        LocalDate end = dates.getEndDate();
        List<AvailabilityDTO> availabilities = availabilityService.getByDates(start, end);
        return ResponseEntity.ok(availabilities);
    }



    // ############################################
    //                  CREATE
    // ############################################
    @PostMapping(path = "/create")
    public ResponseEntity<AvailabilityDTO> createAvailability(@Valid @RequestBody Availability availability) {
        AvailabilityDTO savedAvailability = availabilityService.create(availability);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/availability/create/" + String.valueOf(savedAvailability.getId()));
        return new ResponseEntity<>(savedAvailability, headers, HttpStatus.CREATED);
    }

    // ############################################
    //                  UPDATE
    // ############################################
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<AvailabilityDTO> updateAvailabilityById(@PathVariable("id") Long id, @Valid @RequestBody Availability availability) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/update/" + availability.getId());
        return new ResponseEntity<>(availabilityService.update(id, availability), headers, HttpStatus.OK);
    }


    // ############################################
    //                  DELETE
    // ############################################

    @DeleteMapping(path = "/admin/delete/{id}")
    public ResponseEntity<AvailabilityDTO> deleteAvailabilityById(@PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/admin/delete/" + id);
        return new ResponseEntity<>(availabilityService.delete(id), headers, HttpStatus.OK);
    }


}
