package com.yourproduct.your_product.useCase;

import com.yourproduct.your_product.dto.CategoryDto;
import com.yourproduct.your_product.dto.CategoryRequestDto;

import java.io.IOException;
import java.util.List;

public interface CategoryUseCase {
    CategoryDto addCategory(CategoryRequestDto categoryRequestDto) throws IOException;

    List<CategoryDto> getAllCategories();

    CategoryDto getCategory(Integer categoryId);

    CategoryDto updateCategory(Integer categoryId, CategoryRequestDto updatedCategoryRequestDto) throws IOException;

    void deleteCategory(Integer categoryId);
}
