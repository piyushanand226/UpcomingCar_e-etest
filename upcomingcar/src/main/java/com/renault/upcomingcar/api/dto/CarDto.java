package com.renault.upcomingcar.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CarDto(
    @Positive Integer carId,
    @NotBlank String carName
) {}