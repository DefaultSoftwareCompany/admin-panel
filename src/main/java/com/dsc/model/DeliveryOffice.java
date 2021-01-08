package com.dsc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class DeliveryOffice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short officeId;

    @Column(unique = true)
    private String phoneNumber;

    @OneToOne
    @JoinColumn(unique = true)
    private Address address;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "office")
    @JsonIgnore
    private List<Purchase> purchases;
}
