package com.se.shal.product.graphql;

import com.se.shal.product.dto.InputProductDto;
import com.se.shal.product.dto.ProductDto;
import com.se.shal.product.entity.Product;
import com.se.shal.product.service.ProductService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ProductMutationQL implements GraphQLMutationResolver {

    @Autowired
    ProductService productService;

    @Transactional
    ProductDto saveProduct(Long shopId, InputProductDto product) {
        Product newProduct = productService.saveProduct(shopId, product)  ;
        return ShalMapper.INSTANCE.saveProduct(newProduct);
    }

}
