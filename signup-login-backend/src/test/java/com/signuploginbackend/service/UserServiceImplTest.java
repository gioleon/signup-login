package com.signuploginbackend.service;

import com.signuploginbackend.DTO.UserDTO;
import com.signuploginbackend.model.Rol;
import com.signuploginbackend.model.User;
import com.signuploginbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDTO userDTO;

    private User user;

    @BeforeEach
    void init(){
        userDTO = new UserDTO();
        userDTO.setName("gio");
        userDTO.setEmail("gio@gmail.com");
        userDTO.setLastName("leon");
        userDTO.setId(1);
        userDTO.setPassword("123");

        user = new User();
        user.setName("gio");
        user.setEmail("gio@gmail.com");
        user.setLastName("leon");
        user.setId(0);
        user.setPassword("123");
        user.setRoles(Arrays.asList(new Rol(1, "ROLE_USER")));
    }

    @Test
    void returnListWithUsersInTheDatabase() {
        Mockito.when(this.repository.findAll())
                .thenReturn(Arrays.asList(user));
        assertNotNull(this.userService.findUsers());
        assertEquals(Arrays.asList(user), this.userService.findUsers());

    }

    @Test
    void acceptsUserDTOSaveItAndReturnUser() {
        Mockito.when(this.repository.save(Mockito.any(User.class)))
                .thenReturn(this.user);
        assertEquals(this.user, this.userService.save(this.userDTO));
        assertNotNull(this.userService.save(userDTO));
    }

    @Test
    void takeEmailAndReturnABooleanIfThereIsAUserWithTheGivenEmail() {
        Mockito.when(this.repository.findByEmail("sometext"))
                .thenReturn(Optional.of(new User()));
        assertTrue(this.userService.userExists("sometext"));
        assertFalse(this.userService.userExists("othersometext"));
    }
}