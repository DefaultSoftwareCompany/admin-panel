package com.dsc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetsId;

    private String name;

    private Long imageSize;

    private String contentType;

    private String extension;

    private Long url;

    @OneToOne(mappedBy = "categoryImage", fetch = FetchType.LAZY)
    @JsonIgnore
    private Category category;

    @OneToOne(mappedBy = "productImage", fetch = FetchType.LAZY)
    @JsonIgnore
    private Product product;
}
