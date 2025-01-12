package com.yourproduct.your_product.service.impl;

import com.yourproduct.your_product.entity.Category;
import com.yourproduct.your_product.exception.NotFoundException;
import com.yourproduct.your_product.repository.CategoryRepository;
import com.yourproduct.your_product.service.CategoryService;
import com.yourproduct.your_product.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category", categoryId));
    }

    @Override
    public Category updateCategory(Integer categoryId, Category updatedCategory) {
        Category existingCategory = this.getCategory(categoryId);
        existingCategory.setName(updatedCategory.getName());
        String existingCategoryImage = existingCategory.getImage();
        if (updatedCategory.getImage() != null) existingCategory.setImage(updatedCategory.getImage());
        existingCategory.setParentCategory(updatedCategory.getParentCategory());
        Category savedCategory = categoryRepository.save(existingCategory);
        if (!savedCategory.getImage().equals(existingCategoryImage)) {
            fileUtil.deleteFileAsync(existingCategoryImage);
        }
        return savedCategory;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category existingCategory = this.getCategory(categoryId);
        categoryRepository.deleteById(categoryId);
        fileUtil.deleteFileAsync(existingCategory.getImage());
    }
}
