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

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private float productPrice;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assets_id", nullable = false)
    @JsonIgnore
    private Assets assets;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JoinColumn(name = "firm_id", nullable = false)
    @JsonIgnore
    private Firm firm;

    @Column(name = "date_of_manufacture", nullable = false)
    private String dateOfManufacture;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderedProducts> products;
}
