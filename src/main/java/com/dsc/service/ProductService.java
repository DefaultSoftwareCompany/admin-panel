package com.dsc.service;

import com.dsc.model.Assets;
import com.dsc.model.Category;
import com.dsc.model.Firm;
import com.dsc.model.Product;
import com.dsc.repository.CategoryRepository;
import com.dsc.repository.FirmRepository;
import com.dsc.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final FirmRepository firmRepository;
    private final CategoryRepository categoryRepository;
    private final AssetsService service;

    public ProductService(ProductRepository repository, FirmRepository firmRepository, CategoryRepository categoryRepository, AssetsService service) {
        this.repository = repository;
        this.firmRepository = firmRepository;
        this.categoryRepository = categoryRepository;
        this.service = service;
    }

    public Product getOne(Long productId) {
        return repository.getByProductId(productId);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product save(HttpServletRequest request, MultipartFile multipartFile) throws ParseException, IOException {
        Product product = new Product();
        String productName = request.getParameter("product-name");
        String productPrice = request.getParameter("product-price");
        String dateOfManufacture = request.getParameter("date-of-manufacture");
        String quantity = request.getParameter("quantity");
        String firmId = request.getParameter("firm");
        String categoryId = request.getParameter("category");
        if (productName != null && !productName.isEmpty() && productPrice != null && !productPrice.isEmpty() && dateOfManufacture != null && !dateOfManufacture.isEmpty() && quantity != null && !quantity.isEmpty() && firmId != null && !firmId.isEmpty() && categoryId != null && !categoryId.isEmpty()) {
            product.setProductName(productName);
            product.setProductPrice(Float.valueOf(productPrice));
            product.setQuantity(Long.valueOf(quantity));
            System.out.println(dateOfManufacture);
            product.setDateOfManufacture(dateOfManufacture);
            System.out.println(product.getDateOfManufacture());
            Firm firm = firmRepository.getFirmByFirmId(Long.valueOf(firmId));
            Category category = categoryRepository.getCategoryByCategoryId(Short.valueOf(categoryId));
            product.setFirm(firm);
            product.setCategory(category);
            Assets assets = service.save(multipartFile);
            product.setAssets(assets);
        }
        return repository.save(product);
    }

    public List<Product> getByProductName(String productName) {
        return repository.findAllByProductNameContainingIgnoreCase(productName);
    }

    public List<Product> getByFirm(Long firmId) {
        Firm firm = firmRepository.getFirmByFirmId(firmId);
        return repository.findAllByFirm(firm);
    }

    public List<Product> getByCategory(Short categoryId) {
        Category category = categoryRepository.getCategoryByCategoryId(categoryId);
        return repository.findAllByCategory(category);
    }

    public List<Product> getByPrice(Float price) {
        return repository.findAllByProductPrice(price);
    }

    public List<Product> getByPriceBetween(HttpServletRequest request) {
        Float minPrice = 0f;
        Float maxPrice = Float.MAX_VALUE;
        if (request.getParameter("min-price") != null && !request.getParameter("min-price").isEmpty()) {
            minPrice = Float.parseFloat(request.getParameter("min-price"));
        }
        if (request.getParameter("max-price") != null && !request.getParameter("max-price").isEmpty()) {
            maxPrice = Float.parseFloat(request.getParameter("max-price"));
        }
        return repository.findAllByProductPriceBetween(minPrice, maxPrice);
    }

    public List<Product> getByNameAndPriceBetween(HttpServletRequest request) {
        String productName = request.getParameter("product-name");
        Float minPrice = 0f;
        Float maxPrice = Float.MAX_VALUE;
        if (request.getParameter("min-price") != null && !request.getParameter("min-price").isEmpty()) {
            minPrice = Float.parseFloat(request.getParameter("min-price"));
        }
        if (request.getParameter("max-price") != null && !request.getParameter("max-price").isEmpty()) {
            maxPrice = Float.parseFloat(request.getParameter("max-price"));
        }
        return repository.findAllByProductNameContainingIgnoreCaseAndProductPriceBetween(productName, minPrice, maxPrice);
    }

    public Product delete(Long productId) {
        Product product = repository.getByProductId(productId);
        Assets assets = product.getAssets();
        repository.delete(product);
        service.delete(assets);
        return product;
    }

    public Product edit(Long productId, HttpServletRequest request, MultipartFile assets) throws IOException {
        Product product = repository.getByProductId(productId);
        String productName = request.getParameter("product-name");
        String productPrice = request.getParameter("product-price");
        String dateOfManufacture = request.getParameter("date-of-manufacture");
        String quantity = request.getParameter("quantity");
        String firmId = request.getParameter("firm");
        String categoryId = request.getParameter("category");
        if (productName != null && !productName.isEmpty()) {
            product.setProductName(productName);
        }
        if (productPrice != null && !productPrice.isEmpty()) {
            product.setProductPrice(Float.parseFloat(productPrice));
        }
        if (dateOfManufacture != null && !dateOfManufacture.isEmpty()) {
            product.setDateOfManufacture(dateOfManufacture);
        }
        if (quantity != null && !quantity.isEmpty()) {
            product.setQuantity(Long.parseLong(quantity));
        }
        if (firmId != null && !firmId.isEmpty()) {
            Firm firm = firmRepository.getFirmByFirmId(Long.parseLong(firmId));
            product.setFirm(firm);
        }
        if (categoryId != null && !categoryId.isEmpty()) {
            Category category = categoryRepository.getCategoryByCategoryId(Short.parseShort(categoryId));
            product.setCategory(category);
        }
        if (assets != null) {
            Assets oldAssets = product.getAssets();
            Assets newAssets = service.save(assets);
            product.setAssets(newAssets);
            repository.save(product);
            service.delete(oldAssets);
            return product;
        }
        return repository.save(product);
    }
}
