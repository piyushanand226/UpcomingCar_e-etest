package com.renault.upcomingcar.domain.repository;

import com.renault.upcomingcar.domain.entity.Car;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    // You can add custom query methods here, e.g.:
    Car findByModel(String model);
    List<Car> findByBrand(String brand);
}