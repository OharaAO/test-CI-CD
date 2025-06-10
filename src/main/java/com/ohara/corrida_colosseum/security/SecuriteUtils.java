package com.ohara.corrida_colosseum.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SecuriteUtils {


    public String getRole(AppUserDetails userDetails) {
        return userDetails.getAuthorities().stream()
                .map(r -> r.getAuthority())
                .findFirst()
                .orElse(null);

    }

    public String generateToken(AppUserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .addClaims(Map.of(
                        "id", userDetails.getId(),
                        "firstName", userDetails.getFirstName(),
                        "Phone", userDetails.getPhone(),
                        "role", getRole(userDetails) // tu peux aussi l'intégrer ici si tu veux
                ))
                .signWith(SignatureAlgorithm.HS256, "azerty")
                .compact();
    }


    public String getSubjectFromJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey("azerty")
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();


    }
}
