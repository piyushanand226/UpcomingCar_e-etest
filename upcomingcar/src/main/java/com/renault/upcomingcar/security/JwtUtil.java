package com.renault.upcomingcar.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final byte[] secretBytes;
    private final long jwtValidityMs;

    public JwtUtil(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.validity.ms:3600000}") long jwtValidityMs // default 1h
    ) {
        this.secretBytes = secret.getBytes(StandardCharsets.UTF_8);
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

        var roles = userDetails.getAuthorities()
                               .stream()
                               .map(GrantedAuthority::getAuthority)
                               .collect(Collectors.toList());

        return Jwts.builder()
                   .setSubject(userDetails.getUsername())
                   .claim("roles", roles)
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