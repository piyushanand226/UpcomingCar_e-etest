package com.renault.upcomingcar.api.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.renault.upcomingcar.api.dto.CarDto;
import com.renault.upcomingcar.domain.entity.Car;
import com.renault.upcomingcar.domain.service.CarService;

@RestController
@RequestMapping("/cars")
@Validated
public class CarController {

    private final CarService carService;
    public CarController(CarService carService) { this.carService = carService; }

    @GetMapping
    public List<Car> list() {
        return carService.findAllCars();
    }

    @PostMapping("/admin")
    public Car create(@RequestBody @Validated CarDto dto) {
        Car car = new Car();
        car.setCarId(dto.carId());
        car.setCarName(dto.carName());
        return carService.saveCar(car);
    }

    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable Integer id) {
        carService.deleteCarById(id);
    }
}