package com.renault.upcomingcar.security;

import java.util.Date;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final byte[] secretBytes;
    private final long jwtValidityMs;

    public JwtUtil(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.validity.ms:3600000}") long jwtValidityMs // 1h default
    ) {
        this.secretBytes = secret.getBytes();
        this.jwtValidityMs = jwtValidityMs;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        final Claims claims = parseAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims parseAllClaims(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(Keys.hmacShaKeyFor(secretBytes))
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }

    public String generateToken(UserDetails userDetails) {
        final Date now = new Date();
        final Date exp = new Date(now.getTime() + jwtValidityMs);

        return Jwts.builder()
                   .setSubject(userDetails.getUsername())
                   .claim("roles", userDetails.getAuthorities())
                   .setIssuedAt(now)
                   .setExpiration(exp)
                   .signWith(Keys.hmacShaKeyFor(secretBytes))
                   .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }
}