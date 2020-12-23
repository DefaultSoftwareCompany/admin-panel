package com.dsc.controller;

import com.dsc.model.Address;
import com.dsc.service.AddressService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AddressController {
    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping("/api/address/all")
    public ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("address/address-list");
        modelAndView.addObject("addresses", service.getAll());
        return modelAndView;
    }

    @GetMapping("/api/address/save")
    public ModelAndView savePage(ModelAndView modelAndView) {
        modelAndView.addObject("url", "/api/address/save");
        modelAndView.setViewName("address/address-save");
        modelAndView.addObject("error", "");
        modelAndView.addObject("address", new Address());
        return modelAndView;
    }

    @PostMapping("/api/address/save")
    public ModelAndView save(@ModelAttribute Address address, ModelAndView modelAndView) {
        try {
            address = service.save(address);
            return new ModelAndView("redirect:/api/address/all");
        } catch (Exception e) {
            modelAndView.addObject(address);
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("address/address-save");
        }
        return modelAndView;
    }

    @GetMapping("/api/address/edit/{addressId}")
    public ModelAndView editPage(@PathVariable Long addressId, ModelAndView modelAndView) {
        modelAndView.addObject("url", "/api/address/edit/" + addressId);
        modelAndView.addObject("address", service.getOne(addressId));
        modelAndView.setViewName("address/address-save");
        return modelAndView;
    }

    @PostMapping("/api/address/edit/{addressId}")
    public ModelAndView update(@PathVariable Long addressId, @ModelAttribute Address address, ModelAndView modelAndView) {
        service.edit(addressId, address);
        modelAndView.setViewName("redirect:/api/address/all");
        return modelAndView;
    }

    @GetMapping("/api/address/delete/{addressId}")
    public ModelAndView delete(@PathVariable Long addressId,ModelAndView modelAndView) {
        service.delete(addressId);
        modelAndView.setViewName("redirect:/api/address/all");
        return modelAndView;
    }
}
