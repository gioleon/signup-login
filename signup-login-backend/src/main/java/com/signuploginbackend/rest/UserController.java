package com.signuploginbackend.rest;

import com.signuploginbackend.model.User;
import com.signuploginbackend.service.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserServiceImpl repository;

    UserController(UserServiceImpl repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<User> findAll() {
        return this.repository.findUsers();
    }

}
