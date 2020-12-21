package com.dsc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(name = "district_name", nullable = false)
    private String districtName;

    @Column(name = "street_name", nullable = false)
    private String streetName;

    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private DeliveryOffice office;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private OrderedProducts products;
}
