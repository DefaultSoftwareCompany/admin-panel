package com.dsc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private String cityName;

    private String districtName;

    private String streetName;

    private String houseNumber;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private DeliveryOffice office;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Purchase purchase;
}
