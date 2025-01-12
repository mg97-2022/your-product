package com.yourproduct.your_product.service;

import com.yourproduct.your_product.entity.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);

    List<Category> getAllCategories();

    Category getCategory(Integer categoryId);

    Category updateCategory(Integer categoryId, Category updatedCategory);

    void deleteCategory(Integer categoryId);
}
