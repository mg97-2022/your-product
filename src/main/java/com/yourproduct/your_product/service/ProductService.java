package com.yourproduct.your_product.service;

import com.yourproduct.your_product.entity.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);

    List<Product> getProducts();

    Product getProduct(Integer id);

    Product updateProduct(Product product);

    List<String> deleteProduct(Integer id);
}
