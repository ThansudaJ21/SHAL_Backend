package com.se.shal.product.graphql;

import com.se.shal.product.dto.ProductDto;
import com.se.shal.product.entity.Product;
import com.se.shal.product.service.ProductService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ProductQueryQL implements GraphQLQueryResolver {
    @Autowired
    ProductService productService;

    @Transactional
    ProductDto getProduct(Long id) {
        Product product= productService.getProduct(id);
        return ShalMapper.INSTANCE.getProductDto(product);
    }
}
