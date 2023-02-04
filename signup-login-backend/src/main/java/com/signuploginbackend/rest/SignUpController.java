package com.signuploginbackend.rest;

import com.signuploginbackend.DTO.UserDTO;
import com.signuploginbackend.model.User;
import com.signuploginbackend.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/signup")
public class SignUpController {
    private UserServiceImpl repository;

    SignUpController(UserServiceImpl repository){
        this.repository = repository;
    }

    @GetMapping
    public Iterable<User> findAll(){
        return this.repository.findUsers();
    }

    @PostMapping
    public void signUp(@RequestBody UserDTO userDTO) {
        if (!this.repository.userExists(userDTO.getEmail())) {
            this.repository.save(userDTO);
        } else {
            throw  new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The provided email already belongs to a user"
            );
        }
    }

}
