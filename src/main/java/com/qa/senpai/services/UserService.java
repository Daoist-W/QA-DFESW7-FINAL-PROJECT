package com.qa.senpai.services;

import com.qa.senpai.data.dtos.UserDTO;
import com.qa.senpai.data.entities.User;
import com.qa.senpai.data.repositories.UserRepository;
import com.qa.senpai.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getById(Long id) {
        // TODO: implement me
        if(userRepository.existsById(id)) {
            return mapToDTO(userRepository.getById(id));
        } else {
            throw new UserNotFoundException("User with id: " + id + " does not exist");
        }
    }

    public List<UserDTO> getByName(String forename, String surname) {
        // TODO: test me

        return Optional.of(userRepository
                .findByFullName(forename, surname)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList()))
                .orElseThrow(() -> new UserNotFoundException(
                        "User with name: " + forename + ", " + surname +
                                "does not exist"));
    }

    public List<UserDTO> getByDates(LocalDate availabilityStartDate, LocalDate availabilityEndDate) {
        // TODO: implement me
        return null;
    }


    public UserDTO create(User user) {
        return mapToDTO(userRepository.save(user));
    }


    public UserDTO update(long id, User user) {
        if(userRepository.existsById(id)) {
            User userToUpdate = userRepository.getById(id);
            userToUpdate.setForename(user.getForename());
            userToUpdate.setSurname(user.getSurname());
            userToUpdate.setPosition_(user.getPosition_());
            userToUpdate.setPhoneNum(user.getPhoneNum());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setDob(user.getDob());
            User save = userRepository.save(userToUpdate);
            return mapToDTO(save); // updated user
        } else {
            throw new UserNotFoundException("User with id: " + id + " does not exist");
        }

    }


    public UserDTO delete(Long id) {
        if(userRepository.existsById(id)) {
            UserDTO deletedUser = mapToDTO(userRepository.getById(id));
            userRepository.deleteById(id);
            return deletedUser;
        } else {
            throw new UserNotFoundException("User with id: " + id + " does not exist");
        }
    }
}
