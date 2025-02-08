package com.yourproduct.your_product.controller;

import com.yourproduct.your_product.dto.CategoryDto;
import com.yourproduct.your_product.dto.CategoryRequestDto;
import com.yourproduct.your_product.interfaces.Create;
import com.yourproduct.your_product.interfaces.Update;
import com.yourproduct.your_product.useCase.CategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryUseCase categoryUseCase;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CategoryDto> addCategory(
            @Validated(Create.class) @ModelAttribute CategoryRequestDto categoryRequestDto) throws
            IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryUseCase.addCategory(categoryRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryUseCase.getAllCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(categoryUseCase.getCategory(categoryId));
    }

    @PutMapping(value = "/{categoryId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CategoryDto> updateCategory(
            @Validated(Update.class) @ModelAttribute CategoryRequestDto categoryRequestDto,
            @PathVariable Integer categoryId) throws IOException {
        return ResponseEntity.ok(categoryUseCase.updateCategory(categoryId, categoryRequestDto));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer categoryId) {
        categoryUseCase.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
