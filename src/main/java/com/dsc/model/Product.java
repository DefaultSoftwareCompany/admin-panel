package com.dsc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private float productPrice;

    @Column(name = "quantity")
    private Long quantity;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assets_id")
    @JsonIgnore
    private Assets assets;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JoinColumn(name = "firm_id")
    @JsonIgnore
    private Firm firm;

    @Column(name = "date_of_manufacture")
    private String dateOfManufacture;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderedProducts> products;
}
