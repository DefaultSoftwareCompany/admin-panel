package com.dsc.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Purchase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    @ManyToOne
    @JoinColumn
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User customer;

    private Integer orderQuantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private DeliveryOffice office;

    private String dateOfOrder;

    private byte deadline;

    private Boolean completion;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Address address;
}