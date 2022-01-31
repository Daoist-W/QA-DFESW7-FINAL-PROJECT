package com.qa.senpai.services;

import com.qa.senpai.data.dtos.UserDTO;
import com.qa.senpai.data.entities.User;
import com.qa.senpai.data.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    // Fields
    private UserRepository userRepository;
    private ModelMapper mapper; // allows for mapping DTO's to Entities

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.mapper = modelMapper;
    }


    // ##############################################
    //                   METHODS
    // ##############################################

    private UserDTO mapToDTO(User user) { // maps DTO to User object
        return this.mapper.map(user, UserDTO.class);
    }


    public List<UserDTO> getAll() {
        // TODO: implement me
        return null;
    }

    public UserDTO getById(Long id) {
        // TODO: implement me
        return null;
    }

    public List<UserDTO> getByName(String name) {
        // TODO: implement me
        return null;
    }

    public List<UserDTO> getByDates(List<LocalDate> dates) {
        // TODO: implement me
        return null;
    }


    public UserDTO create(User user) {
        // TODO: implement me
        return mapToDTO(userRepository.save(user));
    }


    public UserDTO update(long id, User user) {
        // TODO: implement me
        return null;
    }


    public UserDTO delete(Long id) {
        // TODO: implement me
        return null;
    }
}
