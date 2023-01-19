package com.signuploginbackend.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class UserDTO implements Serializable {
    private long id;
    private String name;
    private String lastName;
    private String date;
    private String email;
    private String password;



}
