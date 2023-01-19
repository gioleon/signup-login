package com.signuploginbackend.service;

import com.signuploginbackend.DTO.UserDTO;
import com.signuploginbackend.model.User;

public interface UserService {

    void save(UserDTO userDTO);

    Iterable<User> findUsers();

    boolean userExists(String email);

}
