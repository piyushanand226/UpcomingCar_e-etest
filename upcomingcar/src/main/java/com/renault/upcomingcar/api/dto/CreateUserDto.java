package com.renault.upcomingcar.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(
    @NotBlank String username,
    @NotBlank String password,
    @NotBlank String role // "USER" or "ADMIN"
) {}