package com.dsc.service;

import com.dsc.model.Address;
import com.dsc.repository.AddressRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
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
        return repository.getAddressByAddressId(addressId);
    }

    public Address save(Address address) throws Exception {
        if (address.getCityName() == null || address.getDistrictName() == null || address.getStreetName() == null || address.getHouseNumber() == null) {
            throw new Exception("Fill out the form completely!");
        }
        return repository.save(address);
    }

    public Address edit(Long addressId, Address address) {
        Address address1 = repository.getAddressByAddressId(addressId);
        if (address.getCityName() != null && !address.getCityName().isEmpty())
            address1.setCityName(address.getCityName());
        if (address.getDistrictName() != null && !address.getDistrictName().isEmpty())
            address1.setDistrictName(address.getDistrictName());
        if (address.getHouseNumber() != null && !address.getStreetName().isEmpty())
            address1.setStreetName(address.getStreetName());
        if (address.getHouseNumber() != null && !address.getHouseNumber().isEmpty())
            address1.setHouseNumber(address.getHouseNumber());
        return repository.save(address1);
    }

    public void delete(Long addressId) {
        Address address = repository.getAddressByAddressId(addressId);
        repository.delete(address);
        return;
    }
}
