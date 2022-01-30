package com.qa.senpai.controllers;

import com.qa.senpai.data.dtos.UserDTO;
import com.qa.senpai.data.entities.User;
import com.qa.senpai.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    // Fields
    private UserService userService;

    @Autowired
    // TODO: Review your understanding of autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    // ############################################
    //                  READ
    // ############################################

    @GetMapping(path = "/admin") // using post so I can take advantage of the body
    public List<ResponseEntity<UserDTO>> getAllUsers() {
        // TODO: implement access control
        // TODO: implement me
        return null;
    }

    @GetMapping(path = "/admin/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        // TODO: implement me
        return null;
    }

    @GetMapping(path = "/admin/{name}")
    public List<ResponseEntity<UserDTO>> getUsersByName(@PathVariable String name) {
        // TODO: implement access control
        // TODO: implement me
        return null;
    }

    @PostMapping(path = "/admin/available-users") // using post so I can take advantage of the body
    public List<ResponseEntity<UserDTO>> getUsersByAvailability(@RequestBody List<LocalDate> dates) {
        // try the ArrayList argument here, if it doesn't work switch to DateList
        // TODO: implement access control
        // TODO: implement me
        return null;
    }

    // ############################################
    //                  CREATE
    // ############################################
    @PostMapping(path = "/create")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user) {
        // TODO: implement me
        return null;
    }

    // ############################################
    //                  UPDATE
    // ############################################
    @PutMapping(path = "admin/{id}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable("id") Long id, @Valid @RequestBody User user) {
        // TODO: implement access control
        // TODO: implement me
        isAuthorised(0L, ""); // if authorised, proceed, otherwise handle decline
        return null;
    }

    @PutMapping(path = "staff/{id}")
    public ResponseEntity<UserDTO> updateAccount(@PathVariable("id") Long id, @Valid @RequestBody User user) {
        // TODO: implement me
        isAuthorised(0L, ""); // if authorised, proceed, otherwise handle decline
        return null;
    }


    // ############################################
    //                  DELETE
    // ############################################

    @DeleteMapping(path = "admin/{id}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable("id") Long id) {
        // TODO: implement access control
        // TODO: implement me
        return null;
    }

    @DeleteMapping(path = "staff/{id}")
    public ResponseEntity<UserDTO> deleteAccount(@PathVariable("id") Long id, @RequestBody String password) {
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
