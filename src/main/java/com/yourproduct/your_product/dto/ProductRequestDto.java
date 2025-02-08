package com.yourproduct.your_product.dto;

import com.yourproduct.your_product.interfaces.Create;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    private Float price;

    @NotNull(message = "Stock is required")
    private Integer stock;

    @Valid
    @NotNull(message = "Category is required")
    private CategoryDto category;

    @NotNull(message = "Images is required", groups = {Create.class})
    @Size(min = 1, max = 5, message = "Images should be between 1 and 5 images", groups = {Create.class})
    private List<MultipartFile> images;
}
