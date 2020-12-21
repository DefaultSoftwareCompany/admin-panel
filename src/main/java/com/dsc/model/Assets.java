package com.dsc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "assets")
public class Assets implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assets_id")
    private Long assetsId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "hash_id")
    private String hashId;

    @Column(name = "extension")
    private String extension;

    @Column(name = "upload_path")
    private String uploadPath;

    @OneToOne(mappedBy = "assets", fetch = FetchType.LAZY)
    @JsonIgnore
    private Category category;

    @OneToOne(mappedBy = "assets", fetch = FetchType.LAZY)
    @JsonIgnore
    private Product product;
}
