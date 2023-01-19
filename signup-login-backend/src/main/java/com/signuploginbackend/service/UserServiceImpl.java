package com.signuploginbackend.service;

import com.signuploginbackend.DTO.UserDTO;
import com.signuploginbackend.model.Rol;
import com.signuploginbackend.model.User;
import com.signuploginbackend.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<User> findUsers() {
        return this.repository.findAll();
    }

    @Override
    public void save(UserDTO userDTO){
        User user = new User(
                userDTO.getName(), userDTO.getLastName(),
                userDTO.getDateOfBirth(), userDTO.getEmail(),
                userDTO.getPassword(),
                Arrays.asList(new Rol(1,"ROLE_USER"))
        );

        this.repository.save(user);
    }

    @Override
    public boolean userExists(String email) {

        boolean userExists = false;

        User user = this.repository.findByEmail(email).orElse(null);

        if (user != null) {
            userExists = true;
        }
        return userExists;
    }
}
