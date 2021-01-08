package com.dsc.controller;

import com.dsc.model.User;
import com.dsc.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping("/api/customer/save")
    public ResponseEntity<User> save(HttpServletRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping("/api/customer/firstname/{firstName}")
    public ResponseEntity<List<User>> getByFirstName(@PathVariable String firstName) {
        return ResponseEntity.ok(service.getByFirstName(firstName));
    }

    @GetMapping("/api/customer/lastname/{lastName}")
    public ResponseEntity<List<User>> getByLastName(@PathVariable String lastName) {
        return ResponseEntity.ok(service.getByLastName(lastName));
    }

    @GetMapping("/api/customer/get/{customerId}")
    public ResponseEntity<User> getOne(@PathVariable Long customerId) {
        return ResponseEntity.ok(service.getOne(customerId));
    }

    @GetMapping("/api/customer/get/all")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/api/customer/delete/{customerId}")
    public ResponseEntity<User> delete(@PathVariable Long customerId) {
        return ResponseEntity.ok(service.delete(customerId));
    }

    @PostMapping("/api/customer/login")
    public ResponseEntity<User> getCustomer(HttpServletRequest request) {
        return ResponseEntity.ok(service.getByPasswordAndPhoneNumberOrEmail(request));
    }

    @PostMapping("/api/customer/edit/{customerId}")
    public ResponseEntity<User> edit(@PathVariable Long customerId, HttpServletRequest request) {
        return ResponseEntity.ok(service.edit(customerId, request));
    }

    @PostMapping("/api/customer/get/phoneNumber")
    public ResponseEntity<User> getByPhoneNumber(HttpServletRequest request) {
        return ResponseEntity.ok(service.getByPhoneNumber(request));
    }

    @PostMapping("/api/customer/get/email")
    public ResponseEntity<User> getByEmail(HttpServletRequest request) {
        return ResponseEntity.ok(service.getByEmail(request));
    }

}
