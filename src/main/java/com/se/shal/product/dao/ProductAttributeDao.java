package com.se.shal.product.dao;

import com.se.shal.product.entity.Options;
import com.se.shal.product.entity.ProductAttribute;

import java.util.List;

public interface ProductAttributeDao {
    List<ProductAttribute> save(List<ProductAttribute> productAttributes);
    List<ProductAttribute> findAll();
    ProductAttribute getProductAttribute(Long id);
}
