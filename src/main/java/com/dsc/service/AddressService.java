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

    public Address getOne(Long addressId) {
        return repository.getOne(addressId);
    }

    public Address save(Address address) throws Exception {
        if (address.getCityName() == null || address.getDistrictName() == null || address.getStreetName() == null || address.getHouseNumber() == null) {
            throw new Exception("Fill out the form completely!");
        }
        return repository.save(address);
    }


    public void delete(Long addressId) {
        Address address = repository.getOne(addressId);
        repository.delete(address);
    }
}
