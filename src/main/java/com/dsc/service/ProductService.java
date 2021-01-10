package com.dsc.service;

import com.dsc.model.Category;
import com.dsc.model.Firm;
import com.dsc.model.Image;
import com.dsc.model.Product;
import com.dsc.repository.CategoryRepository;
import com.dsc.repository.FirmRepository;
import com.dsc.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final FirmRepository firmRepository;
    private final CategoryRepository categoryRepository;
    private final ImageService service;

    public ProductService(ProductRepository repository, FirmRepository firmRepository, CategoryRepository categoryRepository, ImageService service) {
        this.repository = repository;
        this.firmRepository = firmRepository;
        this.categoryRepository = categoryRepository;
        this.service = service;
    }

    public Product getOne(Long productId) {
        return repository.getOne(productId);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product save(HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        Product product = new Product();
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("image");
        String productName = request.getParameter("product-name");
        String productPrice = request.getParameter("product-price");
        String dateOfManufacture = request.getParameter("date-of-manufacture");
        String quantity = request.getParameter("quantity");
        String firmId = request.getParameter("firm");
        String categoryId = request.getParameter("category");
        if (productName != null && !productName.isEmpty() && productPrice != null && !productPrice.isEmpty() && dateOfManufacture != null && !dateOfManufacture.isEmpty() && quantity != null && !quantity.isEmpty() && firmId != null && !firmId.isEmpty() && categoryId != null && !categoryId.isEmpty() && multipartFile != null && !multipartFile.isEmpty()) {
            product.setProductName(productName);
            product.setProductPrice(Float.valueOf(productPrice));
            product.setQuantity(Long.valueOf(quantity));
            System.out.println(dateOfManufacture);
            product.setDateOfManufacture(dateOfManufacture);
            System.out.println(product.getDateOfManufacture());
            Firm firm = firmRepository.getOne(Long.valueOf(firmId));
            Category category = categoryRepository.getOne(Short.valueOf(categoryId));
            product.setFirm(firm);
            product.setCategory(category);
            Image image = service.save(multipartFile);
            product.setProductImage(image);
            return repository.save(product);
        } else {
            throw new Exception("Fill out the form completely!");
        }
    }


    public void delete(Long productId) {
        repository.deleteById(productId);
    }

    public Product edit(Long productId, HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        Product product = repository.getOne(productId);
        String productName = request.getParameter("product-name");
        String productPrice = request.getParameter("product-price");
        MultipartFile assets = multipartHttpServletRequest.getFile("image");
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
            Firm firm = firmRepository.getOne(Long.parseLong(firmId));
            product.setFirm(firm);
        }
        if (categoryId != null && !categoryId.isEmpty()) {
            Category category = categoryRepository.getOne(Short.parseShort(categoryId));
            product.setCategory(category);
        }
        if (assets != null && !assets.isEmpty()) {
            Image oldImage = product.getProductImage();
            Image newImage = service.save(assets);
            product.setProductImage(newImage);
            repository.save(product);
            service.delete(oldImage.getAssetsId());
            return product;
        }
        return repository.save(product);
    }
}
