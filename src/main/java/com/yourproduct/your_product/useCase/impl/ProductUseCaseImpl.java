package com.yourproduct.your_product.useCase.impl;

import com.yourproduct.your_product.dto.ProductDto;
import com.yourproduct.your_product.dto.ProductRequestDto;
import com.yourproduct.your_product.entity.Product;
import com.yourproduct.your_product.mapper.ProductMapper;
import com.yourproduct.your_product.service.ProductService;
import com.yourproduct.your_product.useCase.ProductUseCase;
import com.yourproduct.your_product.utils.FileUtil;
import com.yourproduct.your_product.utils.FilesFolders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductUseCaseImpl implements ProductUseCase {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final FileUtil fileUtil;

    @Override
    public ProductDto addProduct(ProductRequestDto productRequestDto) {

        List<String> savedImages = fileUtil.saveFiles(productRequestDto.getImages(), FilesFolders.PRODUCTS_FOLDER);
        try {
            Product product = productMapper.toEntityFromProductRequestDto(productRequestDto);
            System.out.println(productRequestDto);
            System.out.println(product);
            product.setImages(savedImages);
            return productMapper.toDto(productService.addProduct(product));
        } catch (Exception ex) {
            fileUtil.deleteFilesAsync(savedImages);
            throw ex;
        }
    }

    @Override
    public List<ProductDto> getProducts() {
        return productMapper.toListDto(productService.getProducts());
    }

    @Override
    public ProductDto getProduct(Integer id) {
        return productMapper.toDto(productService.getProduct(id));
    }

    @Override
    public ProductDto updateProduct(ProductRequestDto productRequestDto, Integer id) {
        return null;
    }

    @Override
    public void deleteProduct(Integer id) {
        List<String> productImages = productService.deleteProduct(id);
        fileUtil.deleteFilesAsync(productImages);
    }
}
