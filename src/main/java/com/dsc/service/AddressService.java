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

    public Address edit(Long addressId, HttpServletRequest request) {
        Address address = repository.getAddressByAddressId(addressId);
        String cityName = request.getParameter("cityName");
        String districtName = request.getParameter("districtName");
        String streetName = request.getParameter("streetName");
        String houseNumber = request.getParameter("houseNumber");
        if (cityName != null && !cityName.isEmpty()) {
            address.setCityName(cityName);
        }
        if (streetName != null && !streetName.isEmpty()) {
            address.setStreetName(streetName);
        }
        if (districtName != null && !districtName.isEmpty()) {
            address.setDistrictName(districtName);
        }
        if (houseNumber != null && !houseNumber.isEmpty()) {
            address.setHouseNumber(houseNumber);
        }
        return repository.save(address);
    }

    public Address delete(Long addressId) {
        Address address = repository.getAddressByAddressId(addressId);
        repository.delete(address);
        return address;
    }
}
