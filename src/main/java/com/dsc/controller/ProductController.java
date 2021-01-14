package com.dsc.controller;

import com.dsc.model.Product;
import com.dsc.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ProductController {
    private final ProductService service;


    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/api/editor/products/all")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @PostMapping("/api/editor/products/save")
    public ResponseEntity<Product> save(HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        return ResponseEntity.ok(service.save(request, multipartHttpServletRequest));
    }

    @DeleteMapping("/api/admin/products/delete/{productId}")
    public void delete(@PathVariable Long productId) {
        service.delete(productId);
    }

    @PutMapping("/api/editor/products/edit/{productId}")
    public ResponseEntity<Product> edit(@PathVariable Long productId, HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        return ResponseEntity.ok(service.edit(productId, request, multipartHttpServletRequest));
    }

}
