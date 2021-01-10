package com.dsc.controller;

import com.dsc.model.Category;
import com.dsc.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/api/editor/category/all")
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/api/admin/category/save")
    public ResponseEntity<Category> save(HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        return ResponseEntity.ok(service.save(request, multipartHttpServletRequest));
    }


    @PostMapping("/api/admin/category/edit/{categoryId}")
    public ResponseEntity<Category> update(@PathVariable Short categoryId, HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        return ResponseEntity.ok(service.edit(categoryId, request, multipartHttpServletRequest));
    }

    @GetMapping("/api/admin/category/delete/{categoryId}")
    public void delete(@PathVariable Short categoryId) {
        service.delete(categoryId);
    }
}
