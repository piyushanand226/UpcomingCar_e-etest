package com.renault.upcomingcar.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Entity
@Table(name = "cars1")
@ToString
@Setter
@Getter
public class Car {

    @Id
    @Column(name = "car_Id", nullable = false)
    private Integer carId;

    @Column(name = "car_Name")
    private String carName;

    @Column(name = "color")
    private String color;

    @Column(name = "car_Type")
    private String carType;

    @Column(name = "model_No")
    private String modelNo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_Manufacture")
    @CreatedDate
    private Date startManufacture;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_Manufacture")
    @CreatedDate
    private Date endManufacture;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_Registration")
    @CreatedDate
    private Date startRegistration;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_Registration")
    @CreatedDate
    private Date endRegistration;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "delivery_Date")
    @CreatedDate
    private Date deliveryDate;

    @Column(name = "price")
    private Integer price;

    @Column(name = "discount_OnRegistration")
    private Integer discountOnRegistration;

    @Column(name = "offer")
    private Integer offer;

    @Column(name = "picByte", length = 1000)
    private byte[] picByte;

    // Relationship with User
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id", referencedColumnName = "user_Id")
    private User user;

    // Relationship with ImageModel
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "image_Id", referencedColumnName = "image_Id")
    private ImageModel imagemodel;
}