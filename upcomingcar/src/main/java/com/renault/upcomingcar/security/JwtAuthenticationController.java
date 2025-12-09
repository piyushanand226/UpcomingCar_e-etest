package com.renault.upcomingcar.security;

import com.renault.upcomingcar.api.dto.AuthRequest;
import com.renault.upcomingcar.api.dto.AuthResponse;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public JwtAuthenticationController(
        AuthenticationManager authenticationManager,
        CustomUserDetailsService userDetailsService,
        JwtUtil jwtUtil
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest request) {
        var authToken = new UsernamePasswordAuthenticationToken(
            request.username(), request.password());

        authenticationManager.authenticate(authToken);

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());
        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}