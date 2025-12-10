package com.renault.upcomingcar.domain.entity;



import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
@Table(name = "image_table")
public class ImageModel implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    public ImageModel() {}

    public ImageModel(String imageName, String type, byte[] picByte) {
        this.imageName = imageName;
        this.type = type;
        this.picByte = picByte;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_Id")
    private Integer imageId;

    @Column(name = "image_Name")
    private String imageName;

    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "picByte")
    private byte[] picByte;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "imageModel", cascade = CascadeType.PERSIST)
    private Car car;
}