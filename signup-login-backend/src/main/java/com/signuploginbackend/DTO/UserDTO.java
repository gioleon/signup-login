package com.signuploginbackend.DTO;

import com.signuploginbackend.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
public class UserDTO implements Serializable {
    private long id;
    private String name;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String password;

    public UserDTO(String email, String password){
        this.email = email;
        this.password = password;
    }

    public UserDTO(
            String name, String lastName, LocalDate dateOfBirth,
            String email, String password){
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
    }
}
