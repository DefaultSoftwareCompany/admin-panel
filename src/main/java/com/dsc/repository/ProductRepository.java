package com.dsc.repository;

import com.dsc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getAllByFirm_FirmName(String firmName);

    List<Product> getAllByCategory_CategoryName(String categoryName);

    List<Product> getAllByProductPriceBetween(Float min, Float max);

    List<Product> getAllByProductNameContainingIgnoreCaseAndProductPriceBetween(String productName, Float min, Float max);

}
