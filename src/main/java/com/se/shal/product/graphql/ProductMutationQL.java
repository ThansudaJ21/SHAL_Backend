package com.se.shal.product.graphql;

import com.se.shal.product.dto.InputProductDto;
import com.se.shal.product.dto.ProductDto;
import com.se.shal.product.dto.SalesInformationDto;
import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.SalesInformation;
import com.se.shal.product.service.ProductService;
import com.se.shal.product.service.SalesInformationService;
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

    @Autowired
    SalesInformationService salesInformationService;

    @Transactional
    ProductDto saveProduct(Long shopId, Product product) {
        Product newProduct = productService.saveProduct(shopId, product)  ;
        return ShalMapper.INSTANCE.saveProduct(newProduct);
    }

    @Transactional
    SalesInformationDto saveSaleInformation(Long productId, SalesInformation salesInformation){
       SalesInformation newSalesInformation = salesInformationService.save(productId,salesInformation);
        return ShalMapper.INSTANCE.saveSaleInformation(newSalesInformation);
    }


}
