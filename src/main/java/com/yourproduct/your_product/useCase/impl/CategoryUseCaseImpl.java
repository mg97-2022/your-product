package com.yourproduct.your_product.useCase.impl;

import com.yourproduct.your_product.dto.CategoryDto;
import com.yourproduct.your_product.dto.CategoryRequestDto;
import com.yourproduct.your_product.entity.Category;
import com.yourproduct.your_product.mapper.CategoryMapper;
import com.yourproduct.your_product.service.CategoryService;
import com.yourproduct.your_product.useCase.CategoryUseCase;
import com.yourproduct.your_product.utils.FileUtil;
import com.yourproduct.your_product.utils.FilesFolders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryUseCaseImpl implements CategoryUseCase {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final FileUtil fileUtil;

    @Override
    public CategoryDto addCategory(CategoryRequestDto categoryRequestDto) throws IOException {
        String savedCategoryImage = fileUtil.saveFile(categoryRequestDto.getImage(), FilesFolders.CATEGORIES_FOLDER);
        try {
            Category category = categoryMapper.toEntityFromCategoryRequestDto(categoryRequestDto);
            category.setImage(savedCategoryImage);
            return categoryMapper.toDto(categoryService.addCategory(category));
        } catch (Exception ex) {
            fileUtil.deleteFileAsync(savedCategoryImage);
            throw ex;
        }
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryMapper.toListDto(categoryService.getAllCategories());
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        return categoryMapper.toDto(categoryService.getCategory(categoryId));
    }

    @Override
    public CategoryDto updateCategory(Integer categoryId, CategoryRequestDto updatedCategoryRequestDto) throws
            IOException {
        String savedCategoryImage =
                fileUtil.saveFile(updatedCategoryRequestDto.getImage(), FilesFolders.CATEGORIES_FOLDER);
        try {
            Category category = categoryMapper.toEntityFromCategoryRequestDto(updatedCategoryRequestDto);
            if (savedCategoryImage != null) category.setImage(savedCategoryImage);
            return categoryMapper.toDto(categoryService.updateCategory(categoryId, category));
        } catch (Exception ex) {
            fileUtil.deleteFile(savedCategoryImage);
            throw ex;
        }
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
