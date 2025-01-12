package com.yourproduct.your_product.mapper;

import com.yourproduct.your_product.dto.CategoryDto;
import com.yourproduct.your_product.dto.CategoryRequestDto;
import com.yourproduct.your_product.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(Category category);

    @Mapping(target = "image", ignore = true)
    Category toEntityFromCategoryRequestDto(CategoryRequestDto categoryRequestDTO);

    List<CategoryDto> toListDto(List<Category> categories);
}
