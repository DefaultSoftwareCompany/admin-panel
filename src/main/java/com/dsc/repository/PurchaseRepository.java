package com.dsc.repository;

import com.dsc.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> getAllByCustomer(User user);

    List<Purchase> getAllByProduct(Product product);

    List<Purchase> getAllByAddress(Address address);

    List<Purchase> getAllByOffice(DeliveryOffice office);

}
