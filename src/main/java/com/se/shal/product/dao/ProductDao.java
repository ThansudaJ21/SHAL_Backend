package com.se.shal.product.dao;

import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.Variations;

import java.util.List;

public interface ProductDao {
    Product saveProduct(Product product);
    Product findById(Long id);
    Product getProduct(Long id);
    List<Product> findAll();
}
