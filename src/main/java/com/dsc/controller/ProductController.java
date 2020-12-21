package com.dsc.controller;

import com.dsc.model.Product;
import com.dsc.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
public class ProductController {
    private final ProductService service;


    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/api/products/get/all")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/api/products/get/{productId}")
    public ResponseEntity<Product> getOne(@PathVariable Long productId) {
        return ResponseEntity.ok(service.getOne(productId));
    }

    @PostMapping("/api/products/save")
    public ResponseEntity<Product> save(HttpServletRequest request, @RequestParam MultipartFile assets) throws IOException, ParseException {
        return ResponseEntity.ok(service.save(request, assets));
    }

    @GetMapping("/api/products/get/product-name/{productName}")
    public ResponseEntity<List<Product>> getByProductName(@PathVariable String productName) {
        return ResponseEntity.ok(service.getByProductName(productName));
    }

    @GetMapping("/api/products/get/firm/{firmId}")
    public ResponseEntity<List<Product>> getByFirm(@PathVariable Long firmId) {
        return ResponseEntity.ok(service.getByFirm(firmId));
    }

    @GetMapping("/api/products/get/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable Short categoryId) {
        return ResponseEntity.ok(service.getByCategory(categoryId));
    }

    @GetMapping("/api/products/get/price/{price}")
    public ResponseEntity<List<Product>> getByPrice(@PathVariable Float price) {
        return ResponseEntity.ok(service.getByPrice(price));
    }

    @GetMapping("/api/products/get/price/between")
    public ResponseEntity<List<Product>> getByPriceBetween(HttpServletRequest request) {
        return ResponseEntity.ok(service.getByPriceBetween(request));
    }

    @GetMapping("/api/products/get/name-price")
    public ResponseEntity<List<Product>> getByNameAndPrice(HttpServletRequest request) {
        return ResponseEntity.ok(service.getByNameAndPriceBetween(request));
    }

    @DeleteMapping("/api/products/delete/{productId}")
    public ResponseEntity<Product> delete(@PathVariable Long productId) {
        return ResponseEntity.ok(service.delete(productId));
    }

    @PutMapping("/api/products/edit/{productId}")
    public ResponseEntity<Product> edit(@PathVariable Long productId, HttpServletRequest request, MultipartFile assets) throws IOException {
        return ResponseEntity.ok(service.edit(productId, request, assets));
    }

}
