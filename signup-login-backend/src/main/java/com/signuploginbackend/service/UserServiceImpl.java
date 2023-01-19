package com.signuploginbackend.service;

import com.signuploginbackend.DTO.UserDTO;
import com.signuploginbackend.model.Rol;
import com.signuploginbackend.model.User;
import com.signuploginbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;

    public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public Iterable<User> findUsers() {
        return this.repository.findAll();
    }

    @Override
    public ResponseEntity<User> save(UserDTO userDTO) {
        if(!userExists(userDTO.getEmail())){
            User user = new User(
                    userDTO.getName(), userDTO.getLastName(),
                    userDTO.getDateOfBirth(), userDTO.getEmail(),
                    userDTO.getPassword(), Arrays.asList(new Rol("ROLE_USER"))
            );

            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(new User(), HttpStatus.NO_CONTENT);

    }

    @Override
    public boolean userExists(String email) {
        return false;
    }
}
