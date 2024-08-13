package com.auth.controllers;

import com.auth.DTOs.PasswordDTO;
import com.auth.DTOs.UserDTO;
import com.auth.repositories.UserRepository;
import com.auth.services.JwtService;
import com.auth.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping
    @Transactional
    public ResponseEntity<UserDTO> resetPassword(HttpServletRequest request, @RequestBody PasswordDTO passwordDTO){
        UserDTO userDTO = userService.verifyUserWithJwt(request, passwordDTO);
        return ResponseEntity.ok(userDTO);
    }


}
