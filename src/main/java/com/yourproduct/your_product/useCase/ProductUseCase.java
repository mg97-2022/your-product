package com.yourproduct.your_product.useCase;

import com.yourproduct.your_product.dto.ProductDto;
import com.yourproduct.your_product.dto.ProductRequestDto;

import java.util.List;

public interface ProductUseCase {
    ProductDto addProduct(ProductRequestDto productRequestDto);

    List<ProductDto> getProducts();

    ProductDto getProduct(Integer id);

    ProductDto updateProduct(ProductRequestDto productRequestDto, Integer id);

    void deleteProduct(Integer id);
}
