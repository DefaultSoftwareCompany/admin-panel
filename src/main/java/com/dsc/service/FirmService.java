package com.dsc.service;

import com.dsc.model.Firm;
import com.dsc.repository.FirmRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class FirmService {
    private final FirmRepository repository;

    public FirmService(FirmRepository repository) {
        this.repository = repository;
    }

    public Firm save(Firm firm) {
        return repository.save(firm);
    }

    public Firm edit(Long firmId, HttpServletRequest request) {
        Firm firm = repository.getFirmByFirmId(firmId);
        String firmName = request.getParameter("firmName");
        String firmEmail = request.getParameter("firmEmail");
        String firmWebsite = request.getParameter("firmWebsite");
        String phoneNumber = request.getParameter("phoneNumber");
        if (!firmName.isEmpty() && firmName != null) {
            firm.setFirmName(firmName);
        }
        if (!firmEmail.isEmpty() && firmEmail != null) {
            firm.setFirmEmail(firmEmail);
        }
        if (firmWebsite != null && !firmWebsite.isEmpty()) {
            firm.setFirmWebsite(firmWebsite);
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            firm.setPhoneNumber(phoneNumber);
        }
        return repository.save(firm);
    }

    public Firm delete(Long firmId) {
        Firm firm = repository.getFirmByFirmId(firmId);
        repository.delete(firm);
        return firm;
    }

    public List<Firm> getAll() {
        return repository.findAll();
    }

    public Firm getOne(Long firmId) {
        return repository.getFirmByFirmId(firmId);
    }
}
