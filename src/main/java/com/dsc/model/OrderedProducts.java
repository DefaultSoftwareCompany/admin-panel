package com.dsc.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ordered_products")
public class OrderedProducts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "product_id" )
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id" )
    private Customer customer;

    @Column(name = "order_quantity" )
    private Integer orderQuantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "office_id" )
    private DeliveryOffice office;

    @Column(name = "date_of_order" )
    private String dateOfOrder;

    @Column(name = "deadline" )
    private byte deadline;

    @Column(name = "completion" )
    private Boolean completion;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id" )
    private Address address;
}
