package com.dsc.controller;

import com.dsc.model.Firm;
import com.dsc.service.FirmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirmController {
    private final FirmService service;

    public FirmController(FirmService service) {
        this.service = service;
    }

    @GetMapping("/api/admin/firm/all")
    public ResponseEntity<List<Firm>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @PostMapping("/api/admin/firm/save")
    public ResponseEntity<Firm> save(@RequestBody Firm firm) throws Exception {
        return ResponseEntity.ok(service.save(firm));
    }


    @PutMapping("/api/admin/firm/edit/{firmId}")
    public ResponseEntity<Firm> update(@PathVariable Long firmId, @ModelAttribute Firm firm) {
        return ResponseEntity.ok(service.edit(firmId, firm));
    }

    @DeleteMapping("/api/admin/firm/delete/{firmId}")
    public void delete(@PathVariable Long firmId) {
        service.delete(firmId);
    }
}
