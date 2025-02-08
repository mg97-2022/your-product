package com.yourproduct.your_product.service.impl;

import com.yourproduct.your_product.entity.Product;
import com.yourproduct.your_product.exception.NotFoundException;
import com.yourproduct.your_product.repository.ProductRepository;
import com.yourproduct.your_product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product"));
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public List<String> deleteProduct(Integer id) {
        Product product = this.getProduct(id);
        productRepository.deleteById(id);
        return product.getImages();
    }
}
