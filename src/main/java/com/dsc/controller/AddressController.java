package com.dsc.controller;

import com.dsc.service.AddressService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

}
