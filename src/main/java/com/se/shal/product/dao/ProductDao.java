package com.se.shal.product.dao;

import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.Variations;
import com.se.shal.product.graphql.entity.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductDao {
    Product saveProduct(Product product);
    Product findById(Long id);
    Product getProduct(Long id);
    List<Product> findAll();
    Page<Product> filterProduct(ProductFilter productFilter, PageRequest pageRequest);
    List<Product> findByShopId(Long shopId);
}
