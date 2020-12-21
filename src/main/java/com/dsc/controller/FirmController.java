package com.dsc.controller;

import com.dsc.model.Firm;
import com.dsc.service.FirmService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FirmController {
    private final FirmService service;

    public FirmController(FirmService service) {
        this.service = service;
    }

    @GetMapping("/api/firm/get/all")
    public String getAll(Model model) {
        model.addAttribute("firms", service.getAll());
        return "admin-panel/firm-list";
    }

    @GetMapping("/api/firm")
    public String getPage() {
        return "admin-panel/add-firm";
    }


    @GetMapping("/api/firm/get/{firmId}")
    public ResponseEntity<Firm> getByFirmId(@PathVariable Long firmId) {
        return ResponseEntity.ok(service.getOne(firmId));
    }

    @PostMapping("/api/firm")
    public String save(@ModelAttribute Firm firm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors());
            model.addAttribute("firm",firm);
            return "admin-panel/add-firm";
        }
        service.save(firm);
        model.addAttribute("firms", service.getAll());
        return "admin-panel/firm-list";
    }

    @PutMapping("/api/firm/edit/{firmId}")
    public ResponseEntity<Firm> update(@PathVariable Long firmId, HttpServletRequest request) {
        return ResponseEntity.ok(service.edit(firmId, request));
    }

    @DeleteMapping("/api/firm/delete/{firmId}")
    public ResponseEntity<Firm> delete(@PathVariable Long firmId) {
        return ResponseEntity.ok(service.delete(firmId));
    }
}
