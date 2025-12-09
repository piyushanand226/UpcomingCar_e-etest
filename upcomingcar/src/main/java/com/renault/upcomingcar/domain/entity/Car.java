package com.renault.upcomingcar.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;

    @Column(nullable = false)
    private String carName;

    // Getters and setters
    public Integer getCarId() { return carId; }
    public void setCarId(Integer carId) { this.carId = carId; }

    public String getCarName() { return carName; }
    public void setCarName(String carName) { this.carName = carName; }
}