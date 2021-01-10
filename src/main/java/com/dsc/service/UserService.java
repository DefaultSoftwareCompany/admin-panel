package com.dsc.service;

import com.dsc.model.Roles;
import com.dsc.model.User;
import com.dsc.repository.RolesRepository;
import com.dsc.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository repository;
    private final RolesRepository rolesRepository;

    public UserService(UserRepository repository, RolesRepository rolesRepository) {
        this.repository = repository;
        this.rolesRepository = rolesRepository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public void delete(Long userId) {
        repository.deleteById(userId);
    }


    public void toggleEditorRole(Long userId) {
        User user = repository.getOne(userId);
        Set<Roles> roles = user.getRoles();
        Roles editorRole = rolesRepository.getByRoleName("EDITOR");
        if (roles.contains(editorRole)) roles.remove(editorRole);
        else roles.add(editorRole);
        user.setRoles(roles);
        repository.save(user);
    }

    public void toggleAdminRole(Long userId) {
        User user = repository.getOne(userId);
        Set<Roles> roles = user.getRoles();
        Roles editorRole = rolesRepository.getByRoleName("ADMIN");
        if (roles.contains(editorRole)) roles.remove(editorRole);
        else roles.add(editorRole);
        user.setRoles(roles);
        repository.save(user);
    }

    public User getOne(Long userId) {
        return repository.getOne(userId);
    }
}
