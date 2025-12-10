package com.renault.upcomingcar.domain.service;

import com.renault.upcomingcar.domain.entity.Car;

public interface CarService {
    @org.springframework.security.access.prepost.PreAuthorize("hasAnyRole('USER','ADMIN')")
    java.util.List<Car> findAllCars();

    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')")
    Car saveCar(Car car);

    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')")
    void deleteCarById(Integer id);
}