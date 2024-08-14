package com.auth.controllers;

import com.auth.DTOs.JwtDTO;
import com.auth.DTOs.UserDTO;
import com.auth.services.AuthService;
import com.auth.services.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth System JWT")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Registra um novo usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Usuário já existente")
    }
    )
    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO userDTO) {
        UserDTO userCreated = authService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @Operation(summary = "Verifica se o usuário passado existe, caso exista, devolve um token JWT", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login efetuado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Usuário/senha inválidos")
    })
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtDTO> loginUser(@RequestBody @Valid UserDTO userDTO){
        String token = authService.authUser(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new JwtDTO(token));
    }

}
