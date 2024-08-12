package com.auth.services;

import com.auth.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {

    @Value("${dotenv.jwt.password}")
    private String passwordJwt;

    public String createTokenJwt(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(passwordJwt);
            return JWT.create()
                    .withIssuer("Auth-System-API")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expiresAt())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Houve um Erro ao Criar o Token", exception);
        }
    }

    public String verifyTokenJwt(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(passwordJwt);
            return JWT.require(algorithm)
                    .withIssuer("Auth-System-API")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token Expirado ou Inválido", exception);
        }
    }

    private Instant expiresAt(){
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }

}
