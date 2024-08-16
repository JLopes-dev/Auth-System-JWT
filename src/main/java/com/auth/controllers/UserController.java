package com.auth.controllers;

import com.auth.DTOs.PasswordDTO;
import com.auth.DTOs.UserDTO;
import com.auth.models.User;
import com.auth.repositories.UserRepository;
import com.auth.services.JwtService;
import com.auth.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository repository;

    @PutMapping
    @Transactional
    public ResponseEntity<UserDTO> resetPassword(HttpServletRequest request, @RequestBody PasswordDTO passwordDTO){
        UserDTO userDTO = userService.verifyUserWithJwt(request, passwordDTO);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping
    public ResponseEntity deleteUser(HttpServletRequest request){
        User user = userService.getUserByTokenJwt(request);
        repository.deleteById(user.getId());
        return ResponseEntity.noContent().build();
    }


}
