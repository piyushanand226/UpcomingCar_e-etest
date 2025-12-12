package com.renault.upcomingcar.domain.service;

import com.renault.upcomingcar.domain.entity.Car;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface CarService {

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    List<Car> findAllCars();

    @PreAuthorize("hasRole('ADMIN')")
    Car saveCar(Car car);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteCarById(Long id);  // ✅ Changed to Long
}
