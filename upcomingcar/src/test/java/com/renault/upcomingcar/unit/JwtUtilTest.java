package com.renault.upcomingcar.unit;

class JwtUtilTest {
    private final com.renault.upcomingcar.security.JwtUtil jwtUtil = new com.renault.upcomingcar.security.JwtUtil("testSecret", 0);

    @org.junit.jupiter.api.Test
    void generateAndValidate() {
        org.springframework.security.core.userdetails.UserDetails ud =
            org.springframework.security.core.userdetails.User.withUsername("piyush").password("x").roles("USER").build();
        String token = jwtUtil.generateToken(ud);
        org.junit.jupiter.api.Assertions.assertNotNull(token);
        org.junit.jupiter.api.Assertions.assertTrue(jwtUtil.validateToken(token, ud));
    }
    
}