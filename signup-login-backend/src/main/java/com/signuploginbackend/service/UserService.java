package com.signuploginbackend.service;

import com.signuploginbackend.DTO.UserDTO;
import com.signuploginbackend.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    Iterable<User> findUsers();
    ResponseEntity save(UserDTO userDTO);

    boolean userExists(String email);

}
