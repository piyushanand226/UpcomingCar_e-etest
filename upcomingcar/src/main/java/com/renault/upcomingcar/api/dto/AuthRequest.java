package com.renault.upcomingcar.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor       // needed for Jackson deserialization
@AllArgsConstructor      // lets you do new AuthRequest(u, p)
public class AuthRequest {
    private String username;
    private String password;
}