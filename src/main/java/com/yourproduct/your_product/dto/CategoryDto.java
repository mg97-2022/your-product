package com.yourproduct.your_product.dto;

import com.yourproduct.your_product.interfaces.Create;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryDto {
    @NotNull(message = "Category id is required")
    Integer id;
    String name;
    String image;
    CategoryDto parentCategory;
}
