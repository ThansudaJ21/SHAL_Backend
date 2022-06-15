package com.se.shal.product.service;


import com.se.shal.product.dto.input.InputProductDto;
import com.se.shal.product.dto.input.InputUpdateProductDto;
import com.se.shal.product.entity.Product;
import com.se.shal.product.graphql.entity.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductService {
    Product saveProduct(Long shopId, InputProductDto product);
    Product getProduct(Long id);
    List<Product> getAllProduct(Long shopId);
    Product updateProduct(InputUpdateProductDto product);
    Product updateProductStatus(Product product);
    Page<Product> productFilter(ProductFilter productFilter, PageRequest pageRequest);
}
