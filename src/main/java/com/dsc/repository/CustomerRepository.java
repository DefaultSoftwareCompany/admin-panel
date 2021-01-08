package com.dsc.repository;

import com.dsc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<User, Long> {
    List<User> findByFirstNameIgnoreCase(String firstName);

    List<User> findByLastNameIgnoreCase(String lastName);

    User getByPhoneNumber(String phoneNumber);

    User getByEmail(String email);

    User getByPasswordAndPhoneNumberOrEmail(String password, String phoneNumber, String email);
}
