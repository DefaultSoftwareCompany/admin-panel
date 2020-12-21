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

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_description")
    private String categoryDescription;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assets_id")
    @JsonIgnore
    private Assets assets;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> products;
}
