package com.yourproduct.your_product.dto;

import com.yourproduct.your_product.interfaces.Create;
import com.yourproduct.your_product.interfaces.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CategoryRequestDto {
    @NotBlank(message = "Category name is required", groups = {Create.class, Update.class})
    @Size(min = 2, max = 100, message = "Category name should be between 2 and 100 characters",
            groups = {Create.class, Update.class})
    public String name;

    @NotNull(message = "Category image is required", groups = Create.class)
    private MultipartFile image;

    private CategoryDto parentCategory;
}
