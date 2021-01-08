package com.dsc.controller;

import com.dsc.model.DeliveryOffice;
import com.dsc.service.OfficeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class OfficeController {
    private final OfficeService service;

    public OfficeController(OfficeService service) {
        this.service = service;
    }

    @GetMapping("/api/office/all")
    public ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("office/office-list");
        modelAndView.addObject("offices", service.getAll());
        return modelAndView;
    }

    @GetMapping("/api/office/save")
    public ModelAndView savePage(ModelAndView modelAndView) {
        modelAndView.addObject("url", "/api/office/save");
        modelAndView.addObject("error", "");
        modelAndView.addObject("office", new DeliveryOffice());
        modelAndView.setViewName("office/office-save");
        return modelAndView;
    }

    @PostMapping("/api/office/save")
    public ModelAndView save(@ModelAttribute DeliveryOffice office, ModelAndView modelAndView) {
        try {
            service.save(office);
            modelAndView.setViewName("redirect:/api/office/all");
        } catch (Exception e) {
            modelAndView.setViewName("office/office-save");
            modelAndView.addObject("url", "/api/office/save");
            modelAndView.addObject("error", e.getMessage());
            modelAndView.addObject(office);
        }
        return modelAndView;
    }

    @GetMapping("/api/office/edit/{officeId}")
    public ModelAndView editPage(@PathVariable Short officeId, ModelAndView modelAndView) {
        modelAndView.addObject("error", "");
        modelAndView.addObject("office", service.getOne(officeId));
        modelAndView.addObject("url", "/api/office/edit/" + officeId);
        modelAndView.setViewName("office/office-save");
        return modelAndView;
    }

    @PostMapping("/api/office/edit/{officeId}")
    public ModelAndView update(@PathVariable Short officeId, @ModelAttribute DeliveryOffice office, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/api/office/all");
        service.edit(officeId, office);
        return modelAndView;
    }

    @GetMapping("/api/office/delete/{officeId}")
    public ModelAndView delete(@PathVariable Short officeId, ModelAndView modelAndView) {
        service.delete(officeId);
        modelAndView.setViewName("redirect:/api/office/all");
        return modelAndView;
    }
}
