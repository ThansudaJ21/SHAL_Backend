package com.se.shal.product.graphql;

import com.se.shal.product.dto.ProductDto;
import com.se.shal.product.dto.query.FilterProductDto;
import com.se.shal.product.dto.query.ProductQuery;
import com.se.shal.product.dto.query.QueryProductDto;
import com.se.shal.product.entity.*;
import com.se.shal.product.service.*;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class ProductQueryQL implements GraphQLQueryResolver {
    @Autowired
    ProductService productService;


    @Transactional
    ProductQuery getProduct(Long id) {
        Product product = productService.getProduct(id);
        Hibernate.initialize(product.getProductStatus());
        return ShalMapper.INSTANCE.getProductDto(product);
    }

    @Transactional
    List<QueryProductDto> getAllProduct(Long shopId){
        List<Product> products = productService.getAllProduct(shopId);
        return ShalMapper.INSTANCE.getQueryAllProductDto(products);
    }

    @Transactional
    List<FilterProductDto> productFilterByCategory(String category) {
        List<Product> products = productService.productFilterByCategory(category);
        return ShalMapper.INSTANCE.getFilterAllProductByCategory(products);
    }

    @Transactional
   List<FilterProductDto> productFilterByStatus(String status) {
        List<Product> products = productService.productFilterByStatus(status);
        return ShalMapper.INSTANCE.getFilterAllProductByStatus(products);
   }
}

