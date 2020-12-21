package com.dsc.controller;

import com.dsc.model.DeliveryOffice;
import com.dsc.service.OfficeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class OfficeController {
    private final OfficeService service;

    public OfficeController(OfficeService service) {
        this.service = service;
    }

    @GetMapping("/api/office/all")
    public ResponseEntity<List<DeliveryOffice>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/api/office/get/{officeId}")
    public ResponseEntity<DeliveryOffice> getOne(@PathVariable Short officeId) {
        return ResponseEntity.ok(service.getOne(officeId));
    }

    @PostMapping("/api/office/save")
    public ResponseEntity<DeliveryOffice> save(HttpServletRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @PutMapping("/api/office/edit/{officeId}")
    public ResponseEntity<DeliveryOffice> update(@PathVariable Short officeId, HttpServletRequest request) {
        return ResponseEntity.ok(service.edit(officeId, request));
    }

    @DeleteMapping("/api/office/delete/{officeId}")
    public ResponseEntity<DeliveryOffice> delete(@PathVariable Short officeId) {
        return ResponseEntity.ok(service.delete(officeId));
    }

    @GetMapping("/api/office/get/district/{districtName}")
    public ResponseEntity<DeliveryOffice> getByDistrict(@PathVariable String districtName) {
        return ResponseEntity.ok(service.getByDistrictName(districtName));
    }

    @GetMapping("/api/office/get/city/{cityName}")
    public ResponseEntity<List<DeliveryOffice>> getByCity(@PathVariable String cityName) {
        return ResponseEntity.ok(service.getByCityName(cityName));
    }
}
