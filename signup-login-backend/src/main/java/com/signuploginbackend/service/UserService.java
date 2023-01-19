package com.signuploginbackend.service;

import com.signuploginbackend.DTO.UserDTO;
import com.signuploginbackend.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    void save(UserDTO userDTO);

    Iterable<User> findUsers();

    boolean userExists(String email);

}
