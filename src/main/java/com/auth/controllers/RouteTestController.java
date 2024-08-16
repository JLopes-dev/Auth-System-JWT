package com.auth.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Testar login")
public class RouteTestController {

    @Operation(summary = "Rota para testar se o usuário está logado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Você está logado"),
            @ApiResponse(responseCode = "403", description = "Sem autorização para acessar a rota, faça o login")
    })
    @GetMapping
    public ResponseEntity<String> testRoute(){
        return ResponseEntity.ok("Usuário Autorizado");
    }

}
