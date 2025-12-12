package com.renault.upcomingcar.service;

import com.renault.upcomingcar.domain.entity.Car;
import com.renault.upcomingcar.domain.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class CarServiceSecurityTest {

    @Autowired
    private CarService carService;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void adminCanSaveCar() {
        Car car = new Car();
        car.setCarName("Kwid");   // ✅ Correct field name
        assertThat(carService.saveCar(car)).isNotNull();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void userCannotSaveCar() {
        Car car = new Car();
        car.setCarName("Duster");
        assertThrows(AccessDeniedException.class, () -> carService.saveCar(car));
    }

}
