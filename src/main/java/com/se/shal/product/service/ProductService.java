package com.se.shal.product.service;


import com.se.shal.product.entity.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Long shopId, Product product);
    Product getProduct(Long id);
    List<Product> getAllProduct(Long shopId);
}
