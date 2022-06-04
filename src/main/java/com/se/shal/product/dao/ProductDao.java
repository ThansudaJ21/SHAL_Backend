package com.se.shal.product.dao;

import com.se.shal.product.entity.Product;

public interface ProductDao {
    Product saveProduct(Product product);
    Product findById(Long id);
    Product getProduct(Long id);
}
