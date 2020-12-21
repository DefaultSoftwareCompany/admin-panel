package com.dsc.repository;

import com.dsc.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Customer getByCustomerId(Long customerId);

    public List<Customer> findByFirstNameIgnoreCase(String firstName);

    public List<Customer> findByLastNameIgnoreCase(String lastName);

    public Customer getByPhoneNumber(String phoneNumber);

    public Customer getByEmail(String email);

    public Customer getByPasswordAndPhoneNumberOrEmail(String password, String phoneNumber, String email);
}
