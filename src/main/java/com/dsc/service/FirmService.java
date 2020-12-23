package com.dsc.service;

import com.dsc.model.Firm;
import com.dsc.repository.FirmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirmService {
    private final FirmRepository repository;

    public FirmService(FirmRepository repository) {
        this.repository = repository;
    }

    public Firm save(Firm firm) throws Exception {
        if (firm.getFirmName() == null || (firm.getFirmEmail() == null && firm.getPhoneNumber() == null && firm.getFirmWebsite() == null)) {
            throw new Exception("Fill out the form completely!");
        } else {
            return repository.save(firm);
        }
    }

    public Firm edit(Long firmId, Firm firm) {
        Firm firm1 = repository.getFirmByFirmId(firmId);
        if (!firm.getFirmName().isEmpty() && firm.getFirmName() != null) {
            firm1.setFirmName(firm.getFirmName());
        }
        if (!firm.getFirmEmail().isEmpty() && firm.getFirmEmail() != null) {
            firm1.setFirmEmail(firm.getFirmEmail());
        }
        if (firm.getFirmWebsite() != null && !firm.getFirmWebsite().isEmpty()) {
            firm1.setFirmWebsite(firm.getFirmWebsite());
        }
        if (firm.getPhoneNumber() != null && !firm.getPhoneNumber().isEmpty()) {
            firm1.setPhoneNumber(firm.getPhoneNumber());
        }
        return repository.save(firm1);
    }

    public void delete(Long firmId) {
        repository.deleteById(firmId);
        return;
    }

    public List<Firm> getAll() {
        return repository.findAll();
    }

    public Firm getOne(Long firmId) {
        return repository.getFirmByFirmId(firmId);
    }
}
