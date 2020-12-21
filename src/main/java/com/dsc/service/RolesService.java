package com.dsc.service;

import com.dsc.model.Roles;
import com.dsc.repository.RolesRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class RolesService {
    private final RolesRepository repository;

    public RolesService(RolesRepository repository) {
        this.repository = repository;
    }

    public Roles save(HttpServletRequest request) {
        Roles roles = new Roles();
        String roleName = request.getParameter("roleName");
        if (roleName != null && !roleName.isEmpty()) {
            roles.setRole(roleName.toUpperCase());
        }
        return repository.save(roles);
    }

    public List<Roles> getAll() {
        return repository.findAll();
    }
}
