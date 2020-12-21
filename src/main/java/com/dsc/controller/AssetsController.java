package com.dsc.controller;

import com.dsc.model.Assets;
import com.dsc.service.AssetsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AssetsController {
    private final AssetsService service;

    public AssetsController(AssetsService service) {
        this.service = service;
    }

    @PostMapping("/api/assets/save")
    public ResponseEntity<Assets> save(@RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.save(file));
    }

    @DeleteMapping("/api/assets/delete/{id}")
    public ResponseEntity<Assets> delete(@PathVariable Long id) {
        Assets assets = service.getOne(id);
        return ResponseEntity.ok(service.delete(assets));
    }
}
