package com.renault.upcomingcar.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renault.upcomingcar.domain.entity.Car;
import com.renault.upcomingcar.domain.repository.CarRepository;
import com.renault.upcomingcar.domain.service.CarService;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteCarById(Long id) {   // ✅ Changed to Long
        carRepository.deleteById(id);
    }
}
