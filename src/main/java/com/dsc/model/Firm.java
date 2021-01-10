package com.dsc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Firm implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long firmId;

    @Column(unique = true)
    private String firmName;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String firmEmail;

    @Column(unique = true)
    private String firmWebsite;

    @OneToMany(mappedBy = "firm")
    @JsonIgnore
    private List<Product> products;
}
