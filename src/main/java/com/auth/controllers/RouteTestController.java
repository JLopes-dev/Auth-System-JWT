package com.auth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class RouteTestController {

    @GetMapping
    public ResponseEntity<String> testRoute(){
        return ResponseEntity.ok("Usuário Autorizado");
    }

}
