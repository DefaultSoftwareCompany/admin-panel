package com.dsc.controller;

import com.dsc.model.DeliveryOffice;
import com.dsc.service.OfficeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class OfficeController {
    private final OfficeService service;

    public OfficeController(OfficeService service) {
        this.service = service;
    }

    @GetMapping("/api/admin/office/all")
    public ResponseEntity<List<DeliveryOffice>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @PostMapping("/api/owner/office/save")
    public ResponseEntity<DeliveryOffice> save(@RequestBody DeliveryOffice office) throws Exception {
        return ResponseEntity.ok(service.save(office));
    }

    @PostMapping("/api/office/owner/edit/{officeId}")
    public ResponseEntity<DeliveryOffice> update(@PathVariable Short officeId, @ModelAttribute DeliveryOffice office, ModelAndView modelAndView) {
        return ResponseEntity.ok(service.edit(officeId, office));
    }

    @DeleteMapping("/api/owner/office/delete/{officeId}")
    public void delete(@PathVariable Short officeId) {
        service.delete(officeId);
    }
}
