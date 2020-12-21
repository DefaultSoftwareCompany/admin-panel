package com.dsc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Short categoryId;

    @Column(name = "category_name", nullable = false, unique = true)
    private String categoryName;

    @Column(name = "category_description", nullable = false)
    private String categoryDescription;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assets_id", nullable = false)
    @JsonIgnore
    private Assets assets;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> products;
}
