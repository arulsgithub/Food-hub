package com.arulJD.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTProvider {

    private final SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());

    public String generateToken(Authentication auth) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 86400000))
                .claim("email", auth.getName())
                .signWith(key)
                .compact();
    }

    public String getEmailFromToken(String jwt) {
        jwt = jwt.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        return String.valueOf(claims.get("email"));
    }
}