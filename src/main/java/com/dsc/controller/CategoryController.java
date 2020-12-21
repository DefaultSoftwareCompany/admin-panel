package com.dsc.controller;

import com.dsc.model.Category;
import com.dsc.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping("/api/category/save")
    public ResponseEntity<Category> save(HttpServletRequest request, MultipartHttpServletRequest mrequest) throws IOException {
        return ResponseEntity.ok(service.save(request, mrequest));
    }

    @PutMapping("/api/category/edit/{categoryId}")
    public ResponseEntity<Category> update(@PathVariable Short categoryId, HttpServletRequest request, MultipartHttpServletRequest mrequest) throws IOException {
        return ResponseEntity.ok(service.edit(categoryId, request, mrequest));
    }

    @DeleteMapping("/api/category/delete/{id}")
    public ResponseEntity<Category> delete(@PathVariable Short id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
