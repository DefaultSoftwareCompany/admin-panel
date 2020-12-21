package com.dsc.repository;

import com.dsc.model.Category;
import com.dsc.model.Firm;
import com.dsc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Product getByProductId(Long productId);

    public List<Product> findAllByFirm(Firm firm);

    public List<Product> findAllByCategory(Category category);

    public List<Product> findAllByProductPrice(Float price);

    public List<Product> findAllByProductNameContainingIgnoreCase(String productName);

    public List<Product> findAllByProductPriceBetween(Float minPrice, Float maxPrice);

    public List<Product> findAllByProductNameContainingIgnoreCaseAndProductPriceBetween(String productName, Float minPrice, Float maxPrice);

}
