package com.renault.upcomingcar.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.renault.upcomingcar.domain.entity.Car;
import com.renault.upcomingcar.domain.repository.CarRepository;
import com.renault.upcomingcar.domain.service.CarService;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepo;

    public CarServiceImpl(CarRepository carRepo) {
        this.carRepo = carRepo;
    }

    @Override
    public List<Car> findAllCars() {
        return carRepo.findAll();
    }

    @Override
    public Car saveCar(Car car) {
        return carRepo.save(car);
    }

    @Override
    public void deleteCarById(Integer id) {
        carRepo.deleteById(id);
    }
}