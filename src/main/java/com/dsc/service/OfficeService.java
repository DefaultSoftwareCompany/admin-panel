package com.dsc.service;

import com.dsc.model.Address;
import com.dsc.model.DeliveryOffice;
import com.dsc.repository.AddressRepository;
import com.dsc.repository.OfficeRepository;
import org.springframework.stereotype.Service;

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
        return repository.getOne(officeId);
    }

    public DeliveryOffice save(DeliveryOffice office) throws Exception {
        if (office.getEmail() != null && !office.getEmail().isEmpty() && office.getPhoneNumber() != null && !office.getPhoneNumber().isEmpty() && office.getAddress() != null) {
            Address address = addressRepository.save(office.getAddress());
            office.setAddress(address);
            return repository.save(office);
        } else {
            throw new Exception("Fill out the form completely!");
        }
    }

    public DeliveryOffice edit(Short officeId, DeliveryOffice office) {
        DeliveryOffice office1 = repository.getOne(officeId);
        if (office.getPhoneNumber() != null && !office.getPhoneNumber().isEmpty()) {
            office1.setPhoneNumber(office.getPhoneNumber());
        }
        if (office.getAddress() != null) {
            Address oldAddress = office1.getAddress();
            Address newAddress = addressRepository.save(office.getAddress());
            office1.setAddress(newAddress);
            repository.save(office1);
            addressRepository.delete(oldAddress);
        }
        if (office.getEmail() != null && !office.getEmail().isEmpty()) {
            office1.setEmail(office.getEmail());
        }

        return repository.save(office1);
    }

    public void delete(Short officeId) {
        DeliveryOffice office = repository.getOne(officeId);
        Address address = office.getAddress();
        repository.delete(office);
        addressRepository.delete(address);
    }

}
