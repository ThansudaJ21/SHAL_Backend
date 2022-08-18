package com.se.shal.product.graphql;

import com.se.shal.product.dto.*;
import com.se.shal.product.dto.input.*;
import com.se.shal.product.entity.*;
import com.se.shal.product.service.*;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class ProductMutationQL implements GraphQLMutationResolver {

    @Autowired
    ProductService productService;

    @Autowired
    VariationsService variationsService;


    @Transactional
    ProductDto saveProduct(Long shopId, InputProductDto product) {
        Product newProduct = productService.saveProduct(shopId, product);
        return ShalMapper.INSTANCE.saveProductDto(newProduct);
    }

    @Transactional
    InputUpdateProductDto updateProduct(InputUpdateProductDto product) {
        Product product1 = productService.updateProduct(product);
        return ShalMapper.INSTANCE.updateProduct(product1);
    }

    @Transactional
    InputUpdateProductStatusDto updateProductStatus(Product product){
        Product product1 = productService.updateProductStatus(product);
        return ShalMapper.INSTANCE.updateProductStatus(product1);
    }

    @Transactional
    void deleteVariations(Long product, Long id){
        variationsService.deleteVariations(product,id);
    }
}
