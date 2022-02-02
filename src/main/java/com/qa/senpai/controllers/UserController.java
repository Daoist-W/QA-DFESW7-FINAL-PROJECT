package com.qa.senpai.controllers;

import com.qa.senpai.data.dtos.UserDTO;
import com.qa.senpai.data.entities.User;
import com.qa.senpai.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    // Fields
    private final UserService userService;

    @Autowired
    // TODO: Review your understanding of autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    // ############################################
    //                  READ
    // ############################################

    @GetMapping(path = "/admin")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        // TODO: implement access control
        List<UserDTO> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/admin/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        UserDTO user = userService.getById(id);
        headers.add("Location", "/user/admin/" + id);
        return new ResponseEntity<UserDTO>(user, headers, HttpStatus.OK);
    }

    @GetMapping(path = "/admin/{forename}/{surname}")
    public ResponseEntity<List<UserDTO>> getUsersByName(@PathVariable String forename, @PathVariable String surname) {
        List<UserDTO> users = userService.getByName(forename, surname);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/user/admin/" + forename + "/" + surname);
        return new ResponseEntity<>(users, headers, HttpStatus.OK);
    }

    @PostMapping(path = "/admin/available-users") // using post so I can take advantage of the body
    public ResponseEntity<List<UserDTO>> getUsersByAvailability(@RequestBody List<LocalDate> dates) {
        // TODO: implement me
        return null;
    }

    // ############################################
    //                  CREATE
    // ############################################
    @PostMapping(path = "/create")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user) {
        // TODO: implement me
        UserDTO savedUser = userService.create(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/user/create/" + String.valueOf(savedUser.getId()));
        return new ResponseEntity<>(savedUser, headers, HttpStatus.CREATED);
    }

    // ############################################
    //                  UPDATE
    // ############################################
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable("id") Long id, @Valid @RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/update/" + user.getId());
        return new ResponseEntity<>(userService.update(id, user), headers, HttpStatus.OK);
    }

    // ############################################
    //                  DELETE
    // ############################################

    @DeleteMapping(path = "/admin/{id}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/admin/delete/" + id);
        return new ResponseEntity<>(userService.delete(id), headers, HttpStatus.OK);
    }




}
