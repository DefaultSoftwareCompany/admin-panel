package com.dsc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "delivery_office")
public class DeliveryOffice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "office_id")
    private Short officeId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "office")
    @JsonIgnore
    private List<OrderedProducts> products;
}
