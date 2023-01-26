package com.signuploginbackend.service;

import com.signuploginbackend.DTO.UserDTO;
import com.signuploginbackend.model.Rol;
import com.signuploginbackend.model.User;
import com.signuploginbackend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<User> findUsers() {
        return this.repository.findAll();
    }

    @Override
    public User save(UserDTO userDTO) {
        User user = new User(
                userDTO.getName(), userDTO.getLastName(),
                userDTO.getEmail(), this.passwordEncoder.encode(userDTO.getPassword()),
                Arrays.asList(new Rol(1, "ROLE_USER"))
        );

        this.repository.save(user);

        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.repository.findByEmail(email);
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
