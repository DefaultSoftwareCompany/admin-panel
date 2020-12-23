package com.dsc.service;

import com.dsc.model.Assets;
import com.dsc.model.Category;
import com.dsc.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final AssetsService assetsService;

    public CategoryService(CategoryRepository repository, AssetsService assetsService) {
        this.repository = repository;
        this.assetsService = assetsService;
    }

    public Category save(HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        MultipartFile file = multipartHttpServletRequest.getFile("file");
        String categoryName = request.getParameter("categoryName");
        String categoryDescription = request.getParameter("categoryDescription");
        Category category = new Category();
        if (categoryName != null && !categoryName.isEmpty() && categoryDescription != null && !categoryDescription.isEmpty() && file != null && !file.isEmpty()) {
            category.setCategoryName(request.getParameter("categoryName"));
            category.setCategoryDescription(request.getParameter("categoryDescription"));
            category.setAssets(assetsService.save(file));
            return repository.save(category);
        } else {
            throw new Exception("Fill out the form completely!");
        }
    }

    public void delete(Short categoryId) {
        Category category = repository.getCategoryByCategoryId(categoryId);
        Assets assets = category.getAssets();
        repository.delete(category);
        assetsService.delete(assets);
        return;
    }

    public Category edit(Short categoryId, HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        Category category = repository.getCategoryByCategoryId(categoryId);
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
            Assets oldAssets = category.getAssets();
            Assets newAssets = assetsService.save(file);
            category.setAssets(newAssets);
            repository.save(category);
            assetsService.delete(oldAssets);
        }
        return repository.save(category);
    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category getOne(Short categoryId) {
        return repository.getCategoryByCategoryId(categoryId);
    }

}
