package com.dsc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "firm")
public class Firm implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "firm_id")
    private Long firmId;

    @Column(name = "firm_name")
    private String firmName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "firm_email")
    private String firmEmail;

    @Column(name = "firm_website")
    private String firmWebsite;

    @OneToMany(mappedBy = "firm", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Product> products;
}
