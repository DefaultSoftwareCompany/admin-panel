package com.dsc.controller;

import com.dsc.model.Image;
import com.dsc.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AssetsController {
    private final ImageService service;

    public AssetsController(ImageService service) {
        this.service = service;
    }

    @PostMapping("/api/assets/save")
    public ResponseEntity<Image> save(@RequestParam MultipartFile file) throws IOException {
        try {
            return ResponseEntity.ok(service.save(file));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/api/assets/delete/{imageId}")
    public void delete(@PathVariable Long imageId) {
        service.delete(imageId);
    }

}
