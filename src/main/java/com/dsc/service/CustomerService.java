package com.dsc.service;

import com.dsc.model.Roles;
import com.dsc.model.User;
import com.dsc.repository.CustomerRepository;
import com.dsc.repository.RolesRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repository;
    private final RolesRepository rolesRepository;

    public CustomerService(CustomerRepository repository, RolesRepository rolesRepository) {
        this.repository = repository;
        this.rolesRepository = rolesRepository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getOne(Long customerId) {
        return repository.getOne(customerId);
    }

    public User save(HttpServletRequest request) {
        Roles roles = rolesRepository.getByRoleName("USER");
        ArrayList<Roles> rolesArrayList = new ArrayList<>();
        rolesArrayList.add(roles);
        User user = new User();
        user.setRoles(rolesArrayList);
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty() && phoneNumber != null && !phoneNumber.isEmpty() && password != null && !password.isEmpty()) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phoneNumber);
            user.setPassword(password);
        }
        user.setEmail(email);
        return repository.save(user);
    }

    public User edit(Long customerId, HttpServletRequest request) {
        User user = repository.getOne(customerId);
        List<Roles> rolesList = user.getRoles();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (firstName != null && !firstName.isEmpty()) {
            user.setFirstName(firstName);
        }
        if (lastName != null && !lastName.isEmpty()) {
            user.setLastName(lastName);
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            user.setPhoneNumber(phoneNumber);
        }
        if (email != null && !email.isEmpty()) {
            user.setEmail(email);
        }
        if (password != null && !password.isEmpty()) {
            user.setPassword(password);
        }
        String[] removedRoles = request.getParameterValues("deleted-roles");
        String[] newRoles = request.getParameterValues("added-roles");
        List<Roles> deletedRoles = new ArrayList<>();
        List<Roles> addedRoles = new ArrayList<>();
        if (removedRoles != null) {
            for (String removedRole : removedRoles) {
                if (!removedRole.isEmpty()) {
                    deletedRoles.add(rolesRepository.getByRoleName(removedRole));
                }
            }
        }
        if (newRoles != null) {
            for (String newRole : newRoles) {
                if (!newRole.isEmpty()) {
                    addedRoles.add(rolesRepository.getByRoleName(newRole));
                }
            }
        }
        rolesList.removeAll(deletedRoles);
        rolesList.addAll(addedRoles);
        user.setRoles(rolesList);
        return repository.save(user);
    }

    public List<User> getByFirstName(String firstName) {
        return repository.findByFirstNameIgnoreCase(firstName);
    }

    public List<User> getByLastName(String lastName) {
        return repository.findByLastNameIgnoreCase(lastName);
    }

    public User getByPhoneNumber(HttpServletRequest request) {
        String phoneNumber = request.getParameter("phoneNumber");
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            return repository.getByPhoneNumber(phoneNumber);
        }
        return null;
    }

    public User getByEmail(HttpServletRequest request) {
        String email = request.getParameter("email");
        if (email != null && !email.isEmpty()) {
            return repository.getByEmail(email);
        }
        return null;
    }

    public User getByPasswordAndPhoneNumberOrEmail(HttpServletRequest request) {
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        if (((phoneNumber != null && !phoneNumber.isEmpty()) || (email != null && !email.isEmpty())) && (password != null && !password.isEmpty())) {
            return repository.getByPasswordAndPhoneNumberOrEmail(password, phoneNumber, email);
        }
        return null;
    }

    public User delete(Long customerId) {
        User user = repository.getOne(customerId);
        repository.delete(user);
        return user;
    }

}
