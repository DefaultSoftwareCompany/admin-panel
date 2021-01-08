package com.dsc.service;

import com.dsc.model.Purchase;
import com.dsc.repository.OfficeRepository;
import com.dsc.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingService {
    private final PurchaseRepository repository;
    private final ProductService productService;
    private final OfficeRepository officeRepository;
    private final CustomerService customerService;

    public ShoppingService(PurchaseRepository repository, ProductService productService, OfficeRepository officeRepository, CustomerService customerService) {
        this.repository = repository;
        this.productService = productService;
        this.officeRepository = officeRepository;
        this.customerService = customerService;
    }

    public Purchase getByOrderId(Long orderId) {
        return repository.getOne(orderId);
    }

    public List<Purchase> getByCustomer(Long customerId) {
        return repository.getAllByCustomer(customerService.getOne(customerId));
    }

    public List<Purchase> getByOffice(Short officeId) {
        return repository.getAllByOffice(officeRepository.getOne(officeId));
    }


    public List<Purchase> getByProduct(Long productId) {
        return repository.getAllByProduct(productService.getOne(productId));
    }

    public Purchase save(Purchase purchase) throws Exception {
        return repository.save(purchase);
    }
}
