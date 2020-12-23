package com.dsc.controller;

import com.dsc.model.Firm;
import com.dsc.service.FirmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FirmController {
    private final FirmService service;

    public FirmController(FirmService service) {
        this.service = service;
    }

    @GetMapping("/api/firm/all")
    public ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("firm/firm-list");
        modelAndView.addObject("firms", service.getAll());
        return modelAndView;
    }

    @GetMapping("/api/firm")
    public ModelAndView savePage(ModelAndView modelAndView) {
        modelAndView.setViewName("firm/firm-save");
        modelAndView.addObject("url", "/api/firm");
        modelAndView.addObject("firm", new Firm());
        modelAndView.addObject("error", "");
        return modelAndView;
    }

    @PostMapping("/api/firm")
    public ModelAndView save(@ModelAttribute Firm firm, ModelAndView modelAndView) {
        try {
            service.save(firm);
            modelAndView.setViewName("redirect:/api/firm/all");
        } catch (Exception e) {
            modelAndView.setViewName("firm/firm-save");
            modelAndView.addObject(firm);
            modelAndView.addObject("error", e.getMessage());
        }
        return modelAndView;
    }

    @GetMapping("/api/firm/edit/{firmId}")
    public ModelAndView editPage(@PathVariable Long firmId, ModelAndView modelAndView) {
        modelAndView.addObject("url", "/api/firm/edit/" + firmId);
        modelAndView.setViewName("firm/firm-save");
        modelAndView.addObject("error", "");
        modelAndView.addObject("firm", service.getOne(firmId));
        return modelAndView;
    }

    @PostMapping("/api/firm/edit/{firmId}")
    public ModelAndView update(@PathVariable Long firmId, @ModelAttribute Firm firm, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/api/firm/all");
        service.edit(firmId, firm);
        return modelAndView;
    }

    @GetMapping("/api/firm/delete/{firmId}")
    public ModelAndView delete(@PathVariable Long firmId, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/api/firm/all");
        service.delete(firmId);
        return modelAndView;
    }
}
