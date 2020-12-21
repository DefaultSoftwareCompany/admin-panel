package com.dsc.service;

import com.dsc.model.Assets;
import com.dsc.model.Category;
import com.dsc.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final AssetsService assetsService;

    public CategoryService(CategoryRepository repository, AssetsService assetsService) {
        this.repository = repository;
        this.assetsService = assetsService;
    }

    public Category save(HttpServletRequest request, MultipartHttpServletRequest mrequest) throws IOException {
        Category category = new Category();
        String categoryName = request.getParameter("categoryName");
        String categoryDescription = request.getParameter("categoryDescription");
        MultipartFile file = mrequest.getFile("file");
        if (categoryName != null && categoryDescription != null && file != null) {
            category.setCategoryName(categoryName);
            category.setCategoryDescription(categoryDescription);
            Assets assets = assetsService.save(file);
            category.setAssets(assets);
            assets.setCategory(category);
            return repository.save(category);
        }
        return null;
    }

    public Category delete(Short categoryId) {
        Category category = repository.getCategoryByCategoryId(categoryId);
        Assets assets = category.getAssets();
        repository.delete(category);
        assetsService.delete(assets);
        return category;
    }

    public Category edit(Short categoryId, HttpServletRequest request, MultipartHttpServletRequest mrequest) throws IOException {
        Category category = repository.getCategoryByCategoryId(categoryId);
        String categoryName = request.getParameter("categoryName");
        String categoryDescription = request.getParameter("categoryDescription");
        MultipartFile file = mrequest.getFile("file");
        if (categoryName != null) {
            category.setCategoryName(categoryName);
        }
        if (categoryDescription != null) {
            category.setCategoryDescription(categoryDescription);
        }
        if (file != null) {
            Assets oldAssets = category.getAssets();
            Assets newAssets = assetsService.save(file);
            category.setAssets(newAssets);
            repository.save(category);
            assetsService.delete(oldAssets);
        }
        return category;
    }

}
