package com.dsc.controller;

import com.dsc.model.Purchase;
import com.dsc.service.ShoppingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final ShoppingService service;

    public OrderController(ShoppingService service) {
        this.service = service;
    }


    @GetMapping("/api/order/get/one/{orderId}")
    public ResponseEntity<Purchase> getOne(@PathVariable Long orderId) throws Exception {
        return ResponseEntity.ok(service.getByOrderId(orderId));
    }

    @GetMapping("/api/order/get/office/{officeId}")
    public ResponseEntity<List<Purchase>> getByOffice(@PathVariable Short officeId) {
        return ResponseEntity.ok(service.getByOffice(officeId));
    }

    @GetMapping("/api/order/get/product/{productId}")
    public ResponseEntity<List<Purchase>> getByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(service.getByProduct(productId));
    }

    @GetMapping("/api/order/get/customer/{customerId}")
    public ResponseEntity<List<Purchase>> getByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(service.getByCustomer(customerId));
    }
}
