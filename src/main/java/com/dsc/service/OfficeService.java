package com.dsc.service;

import com.dsc.model.Address;
import com.dsc.model.DeliveryOffice;
import com.dsc.repository.AddressRepository;
import com.dsc.repository.OfficeRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class OfficeService {
    private final OfficeRepository repository;
    private final AddressRepository addressRepository;

    public OfficeService(OfficeRepository repository, AddressRepository addressRepository) {
        this.repository = repository;
        this.addressRepository = addressRepository;
    }

    public List<DeliveryOffice> getAll() {
        return repository.findAll();
    }

    public DeliveryOffice getOne(Short officeId) {
        return repository.getByOfficeId(officeId);
    }

    public DeliveryOffice save(HttpServletRequest request) {
        String addressId = request.getParameter("addressId");
        DeliveryOffice office = new DeliveryOffice();
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("officeEmail");
        if (email != null && !email.isEmpty() && phoneNumber != null && !phoneNumber.isEmpty() && addressId != null && !addressId.isEmpty()) {
            office.setPhoneNumber(phoneNumber);
            office.setEmail(email);
            Address address = addressRepository.getAddressByAddressId(Long.parseLong(addressId));
            office.setAddress(address);
        }
        return repository.save(office);
    }

    public DeliveryOffice edit(Short officeId, HttpServletRequest request) {
        DeliveryOffice office = repository.getByOfficeId(officeId);
        String addressId = request.getParameter("addressId");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("officeEmail");
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            office.setPhoneNumber(phoneNumber);
        }
        if (addressId != null && !addressId.isEmpty()) {
            office.setAddress(addressRepository.getAddressByAddressId(Long.parseLong(addressId)));
        }
        if (email != null && !email.isEmpty()) {
            office.setEmail(email);
        }

        return repository.save(office);
    }

    public DeliveryOffice delete(Short officeId) {
        DeliveryOffice office = repository.getByOfficeId(officeId);
        Address address = office.getAddress();
        repository.delete(office);
        addressRepository.delete(address);
        return office;
    }

    public DeliveryOffice getByDistrictName(String districtName) {
        return repository.getByDistrictName(districtName);
    }

    public List<DeliveryOffice> getByCityName(String cityName) {
        return repository.getByCityName(cityName);
    }
}
