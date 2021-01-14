package com.dsc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short categoryId;

    @Column(unique = true)
    private String categoryName;

    @Column(columnDefinition = "text")
    private String categoryDescription;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Image categoryImage;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> products;

}
