package com.auth.controllers;

import com.auth.DTOs.JwtDTO;
import com.auth.DTOs.UserDTO;
import com.auth.services.AuthService;
import com.auth.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO userDTO) {
        UserDTO userCreated = authService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> loginUser(@RequestBody @Valid UserDTO userDTO){
        String token = authService.authUser(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new JwtDTO(token));
    }

}
