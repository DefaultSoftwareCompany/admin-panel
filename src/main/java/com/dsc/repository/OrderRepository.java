package com.dsc.repository;

import com.dsc.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderedProducts, Long> {
    public OrderedProducts getByOrderId(Long orderId);

    public List<OrderedProducts> getAllByCustomer(Customer customer);

    public List<OrderedProducts> getAllByProduct(Product product);

    public List<OrderedProducts> getAllByAddress(Address address);

    public List<OrderedProducts> getAllByOffice(DeliveryOffice office);
}
