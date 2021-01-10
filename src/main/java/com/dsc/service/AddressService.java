package com.dsc.service;

import com.dsc.model.Address;
import com.dsc.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public List<Address> getAll() {
        return repository.findAll();
    }


    public Address save(Address address) throws Exception {
        if (address.getCityName() == null || address.getCityName().length() < 5 || address.getDistrictName() == null || address.getDistrictName().length() < 5 || address.getStreetName() == null || address.getStreetName().length() < 5 || address.getHouseNumber() == null || address.getHouseNumber().isEmpty()) {
            throw new Exception("Fill out the form completely!");
        }
        return repository.save(address);
    }


    public void delete(Long addressId) {
        repository.deleteById(addressId);
    }
}
