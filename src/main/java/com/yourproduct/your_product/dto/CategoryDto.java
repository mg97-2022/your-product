package com.yourproduct.your_product.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private Integer id;
    private String name;
    private String image;
    private CategoryDto parentCategory;
}
