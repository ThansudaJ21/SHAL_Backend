package com.se.shal.product.graphql;

import com.se.shal.product.dto.ProductDto;
import com.se.shal.product.dto.QueryShipmentListDto;
import com.se.shal.product.dto.SalesInformationDto;
import com.se.shal.product.dto.ShipmentListDto;
import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.SalesInformation;
import com.se.shal.product.entity.ShipmentList;
import com.se.shal.product.service.ProductService;
import com.se.shal.product.service.SalesInformationService;
import com.se.shal.product.service.ShipmentListService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ProductQueryQL implements GraphQLQueryResolver {
    @Autowired
    ProductService productService;
    @Autowired
    SalesInformationService salesInformationService;

    @Autowired
    ShipmentListService shipmentListService;

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
}
