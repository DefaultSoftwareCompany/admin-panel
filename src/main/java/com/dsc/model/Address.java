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

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "house_number")
    private String houseNumber;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private DeliveryOffice office;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private OrderedProducts products;
}
