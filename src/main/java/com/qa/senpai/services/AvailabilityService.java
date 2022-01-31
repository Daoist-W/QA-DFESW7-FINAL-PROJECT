package com.qa.senpai.services;

import com.qa.senpai.data.dtos.AvailabilityDTO;
import com.qa.senpai.data.entities.Availability;
import com.qa.senpai.data.repositories.AvailabilityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailabilityService {

    // Fields
    private AvailabilityRepository availabilityRepository;
    private ModelMapper mapper; // allows for mapping DTO's to Entities

    @Autowired
    public AvailabilityService(AvailabilityRepository availabilityRepository, ModelMapper modelMapper) {
        this.availabilityRepository = availabilityRepository;
        this.mapper = modelMapper;
    }


    // ##############################################
    //                   METHODS
    // ##############################################

    private AvailabilityDTO mapToDTO(Availability availability) { // maps DTO to Availability object
        return this.mapper.map(availability, AvailabilityDTO.class);
    }


    public List<AvailabilityDTO> getAll() {
        // TODO: implement me
        return null;
    }

    public AvailabilityDTO getById(Long id) {
        // TODO: implement me
        return null;
    }


    public AvailabilityDTO create(Availability availability) {
        // TODO: implement me
        return null;
    }


    public AvailabilityDTO update(long id, Availability availability) {
        // TODO: implement me
        return null;
    }


    public AvailabilityDTO delete(Long id) {
        // TODO: implement me
        return null;
    }
}
