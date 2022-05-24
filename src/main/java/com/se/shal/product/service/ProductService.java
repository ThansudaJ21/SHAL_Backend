package com.se.shal.product.service;

import com.se.shal.product.dto.InputProductDto;
import com.se.shal.product.entity.Product;

public interface ProductService {
    Product saveProduct(Long shopId, Product product);

}
