package com.renault.upcomingcar.domain.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name = "images")
@ToString
@Setter
@Getter
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_Id")
    private Integer imageId;

    @Lob
    @Column(name = "image_data", columnDefinition = "BLOB")
    private byte[] imageData;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    // Bidirectional link back to Car
    @OneToOne(mappedBy = "imagemodel", fetch = FetchType.LAZY)
    private Car car;
}