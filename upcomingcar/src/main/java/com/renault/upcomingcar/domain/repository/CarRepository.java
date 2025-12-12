package com.renault.upcomingcar.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renault.upcomingcar.domain.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

   // Car findByModel(String modelNo);

    //List<Car> findByCarName(String carName);

    //List<Car> findByCarType(String carType);

   // List<Car> findByFuelType(String fuelType);

   // List<Car> findByTransmissionType(String transmissionType);

    //List<Car> findByCarNameContainingIgnoreCase(String carName);
}
