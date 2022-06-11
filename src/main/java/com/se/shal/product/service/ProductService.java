package com.se.shal.product.service;


import com.se.shal.product.dto.input.InputProductDto;
import com.se.shal.product.entity.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Long shopId, InputProductDto product);
    Product getProduct(Long id);
    List<Product> getAllProduct(Long shopId);
    List<Product> productFilterByCategory(String category);
    Product updateProduct(Product product);
    Product updateProductStatus(Product product);
    List<Product> productFilterByStatus(String status);
}
