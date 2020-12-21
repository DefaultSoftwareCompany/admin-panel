package com.dsc.repository;

import com.dsc.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT C FROM Category C WHERE C.categoryId=?1")
    public Category getCategoryByCategoryId(Short categoryId);
}
