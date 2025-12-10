package com.renault.upcomingcar.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
class CarServiceSecurityTest {
    @Autowired private com.renault.upcomingcar.domain.service.CarService carService;

    @Test @WithMockUser(username="admin", roles={"ADMIN"})
    void adminCanSaveCar() throws Exception {
        var car = new com.renault.upcomingcar.domain.entity.Car();
        car.setCarName("Kwid");
        org.assertj.core.api.Assertions.assertThat(carService.saveCar(car)).isNotNull();
    }

    @Test @WithMockUser(username="user", roles={"USER"})
    void userCannotSaveCar() {
        var car = new com.renault.upcomingcar.domain.entity.Car();
        car.setCarName("Duster");
        org.junit.jupiter.api.Assertions.assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> carService.saveCar(car));
    }
}