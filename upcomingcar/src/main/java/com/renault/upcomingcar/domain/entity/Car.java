package com.renault.upcomingcar.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cars1")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_name", nullable = false)
    private String carName;

    @Column(name = "car_type")
    private String carType;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "transmission_type")
    private String transmissionType;

    @Column(name = "price")
    private Double price;

    // One car has many images
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarImage> carImages;
}
