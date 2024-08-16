package com.auth.controllers;

import com.auth.DTOs.PasswordDTO;
import com.auth.DTOs.UserDTO;
import com.auth.models.User;
import com.auth.repositories.UserRepository;
import com.auth.services.JwtService;
import com.auth.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "Recuperar senha e deletar usuário")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository repository;

    @PutMapping
    @Transactional
    @Operation(summary = "Alterar a senha", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Senha modificada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Sem autorização para acessar a rota, faça o login")
    })
    public ResponseEntity<UserDTO> resetPassword(HttpServletRequest request, @RequestBody PasswordDTO passwordDTO){
        UserDTO userDTO = userService.verifyUserWithJwt(request, passwordDTO);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping
    @Transactional
    @Operation(summary = "Deletar usuário", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Sem autorização para acessar a rota, faça o login")
    })
    public ResponseEntity deleteUser(HttpServletRequest request){
        User user = userService.getUserByTokenJwt(request);
        repository.deleteById(user.getId());
        return ResponseEntity.noContent().build();
    }


}
