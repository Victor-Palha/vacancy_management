package com.victorpalha.vacancy_management.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.victorpalha.vacancy_management.exceptions.InvalidToken;
import com.victorpalha.vacancy_management.security.SecurityRoles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class JWTProvider {
    @Value("${security.token.secret}")
    private String JWT_SECRET;

    public String validateToken(String token) throws InvalidToken {
        final String jwt = token.replace("Bearer ", "");
        final Algorithm algorithm = Algorithm.HMAC256(this.JWT_SECRET);
        try {
            return JWT.require(algorithm)
                    .build()
                    .verify(jwt)
                    .getSubject();
        } catch (JWTVerificationException error){
            throw new InvalidToken();
        }
    }

    public String generateToken(String id, SecurityRoles roles) {
        final Instant expirationDateToken = Instant.now().plus(Duration.ofHours(2));
        final Algorithm algorithm = Algorithm.HMAC256(this.JWT_SECRET);

        return JWT.create()
                .withExpiresAt(expirationDateToken)
                .withIssuer("AshFoundation")
                .withClaim("role", roles.name())
                .withSubject(id)
                .sign(algorithm);
    }
}
