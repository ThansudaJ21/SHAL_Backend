package com.se.shal.product.graphql;

import com.se.shal.product.dto.*;
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
    @Autowired
    SalesInformationService salesInformationService;
    @Autowired
    ProductAttributeService productAttributeService;
    @Autowired
    ShipmentListService shipmentListService;
    @Autowired
    VariationsService variationsService;

    @Transactional
    ProductDto getProduct(Long id) {
        Product product = productService.getProduct(id);
        return ShalMapper.INSTANCE.getProductDto(product);
    }

    @Transactional
    SalesInformationDto getSaleInformation(Long productId,Long id) {
        SalesInformation salesInformation = salesInformationService.getSalesInformation(productId,id);
        return ShalMapper.INSTANCE.getSalesInformationDto(salesInformation);
    }

    @Transactional
    QueryShipmentListDto getShipment(Long productId, Long id) {
        ShipmentList shipmentList = shipmentListService.getShipmentLists(productId, id);
        return ShalMapper.INSTANCE.getQueryShipmentListDto(shipmentList);
    }

    @Transactional
    List<QueryProductAttributeDto> getAttribute(Long productId) {
        List<ProductAttribute> productAttributes = productAttributeService.getProductAttribute(productId);
        return ShalMapper.INSTANCE.getQueryProductAttributeDto(productAttributes);
    }

    @Transactional
    List<QueryVariationsDto> getVariations(Long productId) {
        List<Variations> variations = variationsService.getVariations(productId);
        return ShalMapper.INSTANCE.getQueryVariationsDto(variations);
    }
}

