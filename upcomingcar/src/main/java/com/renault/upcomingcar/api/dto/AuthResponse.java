package com.renault.upcomingcar.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor       // needed for Jackson deserialization
@AllArgsConstructor      // lets you do new AuthResponse(token)
public class AuthResponse {
    private String token;
}