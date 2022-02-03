package com.qa.senpai.services;

import com.qa.senpai.data.dtos.AvailabilityDTO;
import com.qa.senpai.data.entities.Availability;
import com.qa.senpai.data.repositories.AvailabilityRepository;
import com.qa.senpai.exceptions.AvailabilityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvailabilityService {

    // Fields
    private final AvailabilityRepository availabilityRepository;
    private final ModelMapper mapper; // allows for mapping DTO's to Entities

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
        return availabilityRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public AvailabilityDTO getById(Long id) {
        if(availabilityRepository.existsById(id)) {
            return mapToDTO(availabilityRepository.getById(id));
        } else {
            throw new AvailabilityNotFoundException("Availability with id: " + id + " does not exist");
        }
    }

    public List<AvailabilityDTO> getByUserId(Long id) {
        return Optional.of(availabilityRepository
                .findByUserId(id)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList()))
                .orElseThrow(() -> new AvailabilityNotFoundException("Availabilitys with user id " + id + " not found"));
    }

    public List<AvailabilityDTO> getByDates(LocalDate availabilityStartDate, LocalDate availabilityEndDate) {
        return Optional.of(availabilityRepository
                .findByDates(availabilityStartDate, availabilityEndDate)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList()))
                .orElseThrow(() -> new AvailabilityNotFoundException("Availability with dates between " +
                        availabilityStartDate + " and " + availabilityEndDate + " not found"));
    }


    public AvailabilityDTO create(Availability availability) {
        return mapToDTO(availabilityRepository.save(availability));
    }


    public AvailabilityDTO update(long id, Availability availability) {
        if(availabilityRepository.existsById(id)) {
            Availability availabilityToUpdate = availabilityRepository.getById(id);
            availabilityToUpdate.setStartDate(availability.getStartDate());
            availabilityToUpdate.setEndDate(availability.getEndDate());
            Availability save = availabilityRepository.save(availabilityToUpdate);
            return mapToDTO(save); // updated availability
        } else {
            throw new AvailabilityNotFoundException("Availability with id: " + id + " does not exist");
        }
    }


    public AvailabilityDTO delete(Long id) {
        if(availabilityRepository.existsById(id)) {
            AvailabilityDTO deletedAvailability = mapToDTO(availabilityRepository.getById(id));
            availabilityRepository.deleteById(id);
            return deletedAvailability;
        } else {
            throw new AvailabilityNotFoundException("Availability with id: " + id + " does not exist");
        }
    }
}
