package com.dsc.service;

import com.dsc.model.Customer;
import com.dsc.model.Roles;
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

    public List<Customer> getAll() {
        return repository.findAll();
    }

    public Customer getOne(Long customerId) {
        return repository.getByCustomerId(customerId);
    }

    public Customer save(HttpServletRequest request) {
        Roles roles = rolesRepository.getByRoleId((short) 2);
        ArrayList<Roles> rolesArrayList = new ArrayList<>();
        rolesArrayList.add(roles);
        Customer customer = new Customer();
        customer.setRoles(rolesArrayList);
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty() && phoneNumber != null && !phoneNumber.isEmpty() && password != null && !password.isEmpty()) {
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setPhoneNumber(phoneNumber);
            customer.setPassword(password);
        }
        customer.setEmail(email);
        return repository.save(customer);
    }

    public Customer edit(Long customerId, HttpServletRequest request) {
        Customer customer = repository.getByCustomerId(customerId);
        List<Roles> rolesList = customer.getRoles();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (firstName != null && !firstName.isEmpty()) {
            customer.setFirstName(firstName);
        }
        if (lastName != null && !lastName.isEmpty()) {
            customer.setLastName(lastName);
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            customer.setPhoneNumber(phoneNumber);
        }
        if (email != null && !email.isEmpty()) {
            customer.setEmail(email);
        }
        if (password != null && !password.isEmpty()) {
            customer.setPassword(password);
        }
        String[] removedRoles = request.getParameterValues("deleted-roles");
        String[] newRoles = request.getParameterValues("added-roles");
        List<Roles> deletedRoles = new ArrayList<>();
        List<Roles> addedRoles = new ArrayList<>();
        if (removedRoles != null) {
            for (String removedRole : removedRoles) {
                if (!removedRole.isEmpty()) {
                    deletedRoles.add(rolesRepository.getByRole(removedRole));
                }
            }
        }
        System.out.println("deletedRoles: " + deletedRoles);
        if (newRoles != null) {
            for (String newRole : newRoles) {
                if (!newRole.isEmpty()) {
                    addedRoles.add(rolesRepository.getByRole(newRole));
                }
            }
        }
        rolesList.removeAll(deletedRoles);
        rolesList.addAll(addedRoles);
        customer.setRoles(rolesList);
        return repository.save(customer);
    }

    public List<Customer> getByFirstName(String firstName) {
        return repository.findByFirstNameIgnoreCase(firstName);
    }

    public List<Customer> getByLastName(String lastName) {
        return repository.findByLastNameIgnoreCase(lastName);
    }

    public Customer getByPhoneNumber(HttpServletRequest request) {
        String phoneNumber = request.getParameter("phoneNumber");
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            return repository.getByPhoneNumber(phoneNumber);
        }
        return null;
    }

    public Customer getByEmail(HttpServletRequest request) {
        String email = request.getParameter("email");
        if (email != null && !email.isEmpty()) {
            return repository.getByEmail(email);
        }
        return null;
    }

    public Customer getByPasswordAndPhoneNumberOrEmail(HttpServletRequest request) {
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        if (((phoneNumber != null && !phoneNumber.isEmpty()) || (email != null && !email.isEmpty())) && !password.isEmpty() && password != null && !password.isEmpty()) {
            return repository.getByPasswordAndPhoneNumberOrEmail(password, phoneNumber, email);
        }
        return null;
    }

    public Customer delete(Long customerId) {
        Customer customer = repository.getByCustomerId(customerId);
        repository.delete(customer);
        return customer;
    }

}
