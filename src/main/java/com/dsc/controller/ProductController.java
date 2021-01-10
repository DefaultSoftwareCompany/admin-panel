package com.dsc.controller;

import com.dsc.model.Product;
import com.dsc.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProductController {
    private final ProductService service;


    public ProductController(ProductService service) {
        this.service = service;
    }


    @PostMapping("/api/editor/products/save")
    public ResponseEntity<Product> save(HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        return ResponseEntity.ok(service.save(request, multipartHttpServletRequest));
    }

    @DeleteMapping("/api/admin/products/delete/{productId}")
    public void delete(@PathVariable Long productId) {
        service.delete(productId);
    }

    @PostMapping("/api/editor/products/edit/{productId}")
    public ResponseEntity<Product> edit(@PathVariable Long productId, HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        return ResponseEntity.ok(service.edit(productId, request, multipartHttpServletRequest));
    }

}
