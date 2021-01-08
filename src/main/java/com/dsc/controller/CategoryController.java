package com.dsc.controller;

import com.dsc.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/api/category/all")
    public ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.addObject("categories", service.getAll());
        modelAndView.setViewName("category/category-list");
        return modelAndView;
    }

    @GetMapping("/api/category/save")
    public ModelAndView savePage(ModelAndView modelAndView) {
        modelAndView.addObject("url", "/api/category/save");
        modelAndView.addObject("error", "");
        modelAndView.setViewName("category/category-save");
        return modelAndView;
    }

    @PostMapping("/api/category/save")
    public ModelAndView save(HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest, ModelAndView modelAndView) {
        try {
            service.save(request, multipartHttpServletRequest);
            modelAndView.setViewName("redirect:/api/category/all");
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("category/category-save");
        }
        return modelAndView;
    }

    @GetMapping("/api/category/edit/{categoryId}")
    public ModelAndView editPage(ModelAndView modelAndView, @PathVariable Short categoryId) {
        modelAndView.setViewName("category/category-save");
        modelAndView.addObject("url", "/api/category/edit/" + categoryId);
        modelAndView.addObject("error", "");
        return modelAndView;
    }

    @PostMapping("/api/category/edit/{categoryId}")
    public ModelAndView update(@PathVariable Short categoryId, HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest, ModelAndView modelAndView) throws Exception {
        service.edit(categoryId, request, multipartHttpServletRequest);
        modelAndView.setViewName("redirect:/api/category/all");
        return modelAndView;
    }

    @GetMapping("/api/category/delete/{categoryId}")
    public ModelAndView delete(@PathVariable Short categoryId, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/api/category/all");
        service.delete(categoryId);
        return modelAndView;
    }
}
