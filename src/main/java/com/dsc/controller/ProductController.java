package com.dsc.controller;

import com.dsc.model.Product;
import com.dsc.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProductController {
    private final ProductService service;


    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/api/products/get/all")
    public ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("products/product-list");
        modelAndView.addObject("products", service.getAll());
        return modelAndView;
    }

    @GetMapping("/api/products/save")
    public ModelAndView getOne(ModelAndView modelAndView) {
        modelAndView.setViewName("products/product-save");
        modelAndView.addObject("error", "");
        modelAndView.addObject("url", "/api/products/save");
        return modelAndView;
    }

    @PostMapping("/api/products/save")
    public ModelAndView save(HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest, ModelAndView modelAndView) {
        try {
            service.save(request, multipartHttpServletRequest);
            modelAndView.setViewName("redirect:/api/products/get/all");
        } catch (Exception e) {
            modelAndView.addObject("url", "/api/products/save");
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("products/product-save");
        }
        return modelAndView;
    }

    @GetMapping("/api/products/edit/{productId}")
    public ModelAndView getByFirm(@PathVariable Long productId, ModelAndView modelAndView) {
        modelAndView.setViewName("products/product-save");
        modelAndView.addObject("url", "/api/products/edit/" + productId);
        modelAndView.addObject("error", "");
        return modelAndView;
    }

    @GetMapping("/api/products/delete/{productId}")
    public ResponseEntity<Product> delete(@PathVariable Long productId) {
        return ResponseEntity.ok(service.delete(productId));
    }

    @PostMapping("/api/products/edit/{productId}")
    public ModelAndView edit(@PathVariable Long productId, HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest, ModelAndView modelAndView) throws Exception {
        service.edit(productId, request, multipartHttpServletRequest);
        modelAndView.setViewName("redirect:/api/products/get/all");
        return modelAndView;
    }

}
