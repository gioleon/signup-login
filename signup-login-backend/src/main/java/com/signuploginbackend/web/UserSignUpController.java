package com.signuploginbackend.web;

import com.signuploginbackend.DTO.UserDTO;
import com.signuploginbackend.model.User;
import com.signuploginbackend.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@RestController
@RequestMapping("/")
@CrossOrigin("http:localhost:4200/")
public class UserSignUpController {
    private UserServiceImpl repository;

    UserSignUpController(UserServiceImpl repository){
        this.repository = repository;
    }

    @GetMapping()
    public Iterable<User> findAll(){
        return this.repository.findUsers();
    }

    @PostMapping("signup")
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
