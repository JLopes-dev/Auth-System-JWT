package com.auth.services;

import com.auth.DTOs.UserDTO;
import com.auth.models.User;
import com.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    public UserDTO createUser(UserDTO userDTO) {
        String passwordHash = new BCryptPasswordEncoder().encode(userDTO.password());
        User user = repository.save(new User(userDTO.username(), passwordHash));
        return new UserDTO(user.getUsername(), user.getPassword());
    }
}
