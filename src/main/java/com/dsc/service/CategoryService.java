package com.dsc.service;

import com.dsc.model.Category;
import com.dsc.model.Image;
import com.dsc.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final ImageService imageService;

    public CategoryService(CategoryRepository repository, ImageService imageService) {
        this.repository = repository;
        this.imageService = imageService;
    }

    public Category save(HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        MultipartFile file = multipartHttpServletRequest.getFile("file");
        String categoryName = request.getParameter("categoryName");
        String categoryDescription = request.getParameter("categoryDescription");
        Category category = new Category();
        if (categoryName != null && !categoryName.isEmpty() && categoryDescription != null && !categoryDescription.isEmpty() && file != null && !file.isEmpty()) {
            category.setCategoryName(request.getParameter("categoryName"));
            category.setCategoryDescription(request.getParameter("categoryDescription"));
            category.setCategoryImage(imageService.save(file));
            return repository.save(category);
        } else {
            throw new Exception("Fill out the form completely!");
        }
    }

    public void delete(Short categoryId) {
        Category category = repository.getOne(categoryId);
        Image image = category.getCategoryImage();
        repository.delete(category);
        imageService.delete(image.getAssetsId());
    }

    public Category edit(Short categoryId, HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        Category category = repository.getOne(categoryId);
        MultipartFile file = multipartHttpServletRequest.getFile("file");
        String categoryName = request.getParameter("categoryName");
        String categoryDescription = request.getParameter("categoryDescription");
        if (categoryName != null && !categoryName.isEmpty()) {
            category.setCategoryName(categoryName);
        }
        if (categoryDescription != null && !categoryDescription.isEmpty()) {
            category.setCategoryDescription(categoryDescription);
        }
        if (file != null && !file.isEmpty()) {
            Image oldImage = category.getCategoryImage();
            Image newImage = imageService.save(file);
            category.setCategoryImage(newImage);
            repository.save(category);
            imageService.delete(oldImage.getAssetsId());
        }
        return repository.save(category);
    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category getOne(Short categoryId) {
        return repository.getOne(categoryId);
    }

}
