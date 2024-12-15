package com.victorpalha.vacancy_management.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.victorpalha.vacancy_management.exceptions.InvalidToken;
import com.victorpalha.vacancy_management.security.SecurityRoles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTProvider {
    @Value("${security.token.secret}")
    private String JWT_SECRET;

    public Map<String, Object> validateToken(String token) throws InvalidToken {
        final String jwt = token.replace("Bearer ", "");
        final Algorithm algorithm = Algorithm.HMAC256(this.JWT_SECRET);
        try {
            final DecodedJWT decodedJWT = JWT.require(algorithm)
                    .build()
                    .verify(jwt);
            final String sub = decodedJWT.getSubject();
            final String role = decodedJWT.getClaim("role").asString();

            Map<String, Object> jwtInformation = new HashMap<>();
            jwtInformation.put("sub", sub);
            jwtInformation.put("role", role);

            return jwtInformation;
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
