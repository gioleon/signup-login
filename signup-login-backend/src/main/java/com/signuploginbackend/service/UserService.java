package com.signuploginbackend.service;

import com.signuploginbackend.DTO.UserDTO;
import com.signuploginbackend.model.User;

import java.util.Optional;

public interface UserService {

    User save(UserDTO userDTO);

    Optional<User> findByEmail(String email);

    Iterable<User> findUsers();

    boolean userExists(String email);


}
