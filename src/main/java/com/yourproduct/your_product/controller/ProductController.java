package com.yourproduct.your_product.controller;

import com.yourproduct.your_product.dto.ProductDto;
import com.yourproduct.your_product.dto.ProductRequestDto;
import com.yourproduct.your_product.useCase.ProductUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductUseCase productUseCase;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductDto> addProduct(
            @Valid @ModelAttribute @RequestBody ProductRequestDto productRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productUseCase.addProduct(productRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(productUseCase.getProducts());
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Integer productId) {
        return ResponseEntity.ok(productUseCase.getProduct(productId));
    }

    @PutMapping(value = "/{productId}")
    public ResponseEntity<ProductDto> updateProduct(
            @Valid @ModelAttribute @RequestBody ProductRequestDto productRequestDto, @PathVariable Integer productId) {
        return ResponseEntity.ok(productUseCase.updateProduct(productRequestDto, productId));
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        productUseCase.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
