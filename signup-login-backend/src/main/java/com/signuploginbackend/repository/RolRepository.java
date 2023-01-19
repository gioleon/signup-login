package com.signuploginbackend.repository;

import com.signuploginbackend.DTO.UserDTO;
import com.signuploginbackend.model.Rol;
import com.signuploginbackend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface RolRepository extends CrudRepository<Rol, Integer> {
}
