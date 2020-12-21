package com.dsc.controller;

import com.dsc.model.Roles;
import com.dsc.service.RolesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class RolesController {
    private final RolesService service;

    public RolesController(RolesService service) {
        this.service = service;
    }

    @PostMapping("/api/roles/save")
    public ResponseEntity<Roles> save(HttpServletRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping("/api/roles/all")
    public ResponseEntity<List<Roles>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
