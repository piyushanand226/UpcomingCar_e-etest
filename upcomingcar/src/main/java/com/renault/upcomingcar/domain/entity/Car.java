package com.renault.upcomingcar.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cars1")   // ✅ FIXED
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;

    @Column(name = "car_name")
    private String carName;

    @Column(name = "car_type")
    private String carType;

    @Column(name = "model_no")
    private String modelNo;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "transmission_type")
    private String transmissionType;

    @Column(name = "price")
    private Double price;
}
