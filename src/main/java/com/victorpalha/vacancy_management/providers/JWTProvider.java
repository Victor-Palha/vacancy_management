package com.victorpalha.vacancy_management.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class JWTProvider {
    @Value("${security.token.secret}")
    private String JWT_SECRET;

    public String validateToken(String token) {
        final String jwt = token.replace("Bearer ", "");
        final Algorithm algorithm = Algorithm.HMAC256(this.JWT_SECRET);
        try {
            return JWT.require(algorithm)
                    .build()
                    .verify(jwt)
                    .getSubject();
        } catch (JWTVerificationException error){
            return error.getMessage();
        }
    }

    public String generateToken(String id) {
        final Instant expirationDateToken = Instant.now().plus(Duration.ofHours(2));
        final Algorithm algorithm = Algorithm.HMAC256(this.JWT_SECRET);
        return JWT.create()
                .withExpiresAt(expirationDateToken)
                .withIssuer("AshFoundation")
                .withSubject(id)
                .sign(algorithm);
    }
}
