package com.dsc.service;

import com.dsc.model.Purchase;
import com.dsc.repository.OfficeRepository;
import com.dsc.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingService {
    private final PurchaseRepository repository;
    private final ProductService productService;
    private final OfficeRepository officeRepository;
    private final UserService userService;

    public ShoppingService(PurchaseRepository repository, ProductService productService, OfficeRepository officeRepository, UserService userService) {
        this.repository = repository;
        this.productService = productService;
        this.officeRepository = officeRepository;
        this.userService = userService;
    }

    public Purchase getByOrderId(Long orderId) throws Exception {
        Optional<Purchase> purchase = repository.findById(orderId);
        if (purchase.isPresent()) return purchase.get();
        else throw new Exception("There is no purchase with such and id");
    }

    public List<Purchase> getByCustomer(Long customerId) {
        return repository.getAllByCustomer(userService.getOne(customerId));
    }

    public List<Purchase> getByOffice(Short officeId) {
        return repository.getAllByOffice(officeRepository.getOne(officeId));
    }


    public List<Purchase> getByProduct(Long productId) {
        return repository.getAllByProduct(productService.getOne(productId));
    }
}
