package com.se.shal.product.graphql;



import com.se.shal.product.dto.query.ProductQuery;
import com.se.shal.product.dto.query.QueryProductDto;
import com.se.shal.product.entity.*;
import com.se.shal.product.graphql.entity.ProductFilter;
import com.se.shal.product.service.*;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    Page<Product> productFilter(ProductFilter filter, int pageNo, int pageSize){
        return productService.productFilter(filter, PageRequest.of(pageNo,pageSize));
    }
}

