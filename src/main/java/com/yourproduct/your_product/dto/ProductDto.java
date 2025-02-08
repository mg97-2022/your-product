package com.yourproduct.your_product.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    Integer id;
    List<String> images;
    private String name;
    private String description;
    private Float price;
    private Integer stock;
    private CategoryDto category;
}
