package com.dsc.service;

import com.dsc.model.*;
import com.dsc.repository.AddressRepository;
import com.dsc.repository.OfficeRepository;
import com.dsc.repository.OrderRepository;
import com.dsc.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final ProductService productService;
    private final AddressService addressService;
    private final OfficeRepository officeRepository;
    private final CustomerService customerService;

    public OrderService(OrderRepository repository, ProductRepository productRepository, AddressRepository addressRepository, ProductService productService, AddressService addressService, OfficeRepository officeRepository, CustomerService customerService) {
        this.repository = repository;
        this.productService = productService;
        this.addressService = addressService;
        this.officeRepository = officeRepository;
        this.customerService = customerService;
    }

    public OrderedProducts getByOrderId(Long orderId) {
        return repository.getByOrderId(orderId);
    }

    public List<OrderedProducts> getByCustomer(Long customerId) {
        return repository.getAllByCustomer(customerService.getOne(customerId));
    }

    public List<OrderedProducts> getByOffice(Short officeId) {
        return repository.getAllByOffice(officeRepository.getByOfficeId(officeId));
    }

    public List<OrderedProducts> getByAddress(Long addressId) {
        return repository.getAllByAddress(addressService.getOne(addressId));
    }

    public List<OrderedProducts> getByProduct(Long productId) {
        return repository.getAllByProduct(productService.getOne(productId));
    }

    public OrderedProducts save(HttpServletRequest request) {
        Address address = addressService.save(request);
        OrderedProducts orderedProducts = new OrderedProducts();
        orderedProducts.setAddress(address);
        String productId = request.getParameter("productId");
        String orderQuantity = request.getParameter("quantity");
        String customerId = request.getParameter("customerId");
        Random random = new Random();
        if (productId != null && !productId.isEmpty()) {
            Product product = productService.getOne(Long.parseLong(productId));
            orderedProducts.setProduct(product);
        }
        if (orderQuantity != null && !orderQuantity.isEmpty()) {
            orderedProducts.setOrderQuantity(Integer.parseInt(orderQuantity));
        }
        if (customerId != null && !customerId.isEmpty()) {
            Customer customer = customerService.getOne(Long.parseLong(customerId));
            orderedProducts.setCustomer(customer);
        }
        if (officeRepository.getByDistrictName(address.getDistrictName()) != null) {
            orderedProducts.setOffice(officeRepository.getByDistrictName(address.getDistrictName()));
        } else {
            List<DeliveryOffice> officeList = officeRepository.getByCityName(address.getCityName());
            orderedProducts.setOffice(officeList.get(random.nextInt(officeList.size() + 1)));
        }
        orderedProducts.setCompletion(false);
        orderedProducts.setDeadline((byte) 20);
        orderedProducts.setDateOfOrder(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return repository.save(orderedProducts);
    }
}
