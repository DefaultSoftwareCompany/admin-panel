package com.dsc.controller;

import com.dsc.model.Address;
import com.dsc.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class AddressController {
    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping("/api/address/all/")
    public String getAll() {

        return "address/address-list";
    }

    @GetMapping("/api/address/get/{addressId}")
    public ResponseEntity<Address> getOne(@PathVariable Long addressId) {
        return ResponseEntity.ok(service.getOne(addressId));
    }

    @PostMapping("/api/address/save")
    public ModelAndView save(@ModelAttribute Address address, ModelAndView modelAndView) {
        modelAndView.setViewName("address/address-save");
        modelAndView.addObject(address);
        return modelAndView;
    }

    @PutMapping("/api/address/edit/{addressId}")
    public ResponseEntity<Address> update(@PathVariable Long addressId, HttpServletRequest request) {
        return ResponseEntity.ok(service.edit(addressId, request));
    }

    @DeleteMapping("/api/address/delete/{addressId}")
    public ResponseEntity<Address> delete(@PathVariable Long addressId) {
        return ResponseEntity.ok(service.delete(addressId));
    }
}
