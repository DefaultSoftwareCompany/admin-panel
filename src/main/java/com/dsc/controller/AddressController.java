package com.dsc.controller;

import com.dsc.model.Address;
import com.dsc.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {
    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping("/api/owner/address/save")
    public ResponseEntity<Address> save(@RequestBody Address address) throws Exception {
        return ResponseEntity.ok(service.save(address));
    }


    @GetMapping("/api/owner/address/all")
    public ResponseEntity<List<Address>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
