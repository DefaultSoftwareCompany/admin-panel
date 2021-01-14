package com.dsc.service;

import com.dsc.model.Firm;
import com.dsc.repository.FirmRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirmService {
    private final FirmRepository repository;

    public FirmService(FirmRepository repository) {
        this.repository = repository;
    }

    public Firm save(Firm firm) throws Exception {
        if (firm.getFirmName() == null || firm.getFirmName().length() < 3 || ((firm.getFirmEmail() == null || !EmailValidator.getInstance().isValid(firm.getFirmEmail())) && firm.getPhoneNumber() == null && firm.getFirmWebsite() == null)) {
            throw new Exception("Fill out the form completely!");
        } else {
            return repository.save(firm);
        }
    }

    public Firm edit(Long firmId, Firm firm) {
        Firm firm1 = repository.getOne(firmId);
        if (!firm.getFirmName().isEmpty()) {
            firm1.setFirmName(firm.getFirmName());
        }
        if (!firm.getFirmEmail().isEmpty() && firm.getFirmEmail() != null && EmailValidator.getInstance().isValid(firm.getFirmEmail())) {
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
    }

    public List<Firm> getAll() {
        return repository.findAll();
    }

    public Firm getOne(Long firmId) {
        return repository.getOne(firmId);
    }
}
