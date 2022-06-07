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
    SalesInformationService salesInformationService;
    @Autowired
    VariationsService variationsService;
    @Autowired
    ShipmentListService shipmentListService;
    @Autowired
    ProductAttributeService productAttributeService;

    @Transactional
    InputProductDto saveProduct(Long shopId, Product product) {
        Product newProduct = productService.saveProduct(shopId, product);
        return ShalMapper.INSTANCE.saveProduct(newProduct);
    }

    @Transactional
    InputSalesInformationDto saveSaleInformation(Long productId, SalesInformation salesInformation) {
        SalesInformation newSalesInformation = salesInformationService.save(productId, salesInformation);
        return ShalMapper.INSTANCE.saveSaleInformation(newSalesInformation);
    }

    @Transactional
    List<VariationsDto> saveVariations(Long productId, List<Variations> variations) {
        List<Variations> newVariations = variationsService.save(productId, variations);
        return ShalMapper.INSTANCE.saveVariations(newVariations);
    }

    @Transactional
    ShipmentListDto saveShipment(Long productId, InputShipmentList shipmentList) {
        ShipmentList shipmentList1 = shipmentListService.save(productId, shipmentList);
        return ShalMapper.INSTANCE.getShipmentListDto(shipmentList1);
    }

    @Transactional
    List<ProductAttributeDto> saveAttribute(Long productId, List<InputProductAttributeDto> inputProductAttributeDto) {
        List<ProductAttribute> productAttributes = productAttributeService.save(productId, inputProductAttributeDto);
        return ShalMapper.INSTANCE.getProductAttributeDto(productAttributes);
    }

    //    Update method
    @Transactional
    InputUpdateProductDto updateProduct(Product product) {
        Product product1 = productService.updateProduct(product);
        return ShalMapper.INSTANCE.updateProduct(product1);
    }

    @Transactional
    InputUpdateSalesInformationDto updateSaleInformation(SalesInformation salesInformation) {
        SalesInformation salesInformation1 = salesInformationService.updateSalesInformation(salesInformation);
        return ShalMapper.INSTANCE.updateSaleInformation(salesInformation1);
    }

    @Transactional
    UpdateShipmentList updateShipments( InputUpdateShipmentList shipmentList) {
        ShipmentList shipmentList1 = shipmentListService.updateShipmentLists(shipmentList);
        return ShalMapper.INSTANCE.getUpdateShipmentListDto(shipmentList1);
    }

    @Transactional
    List<InputUpdateVariationsDto> updateVariations(Long productId,List<Variations> variations) {
        List<Variations> updateVariations = variationsService.updateVariations(productId,variations);
        return ShalMapper.INSTANCE.updateVariations(updateVariations);
    }

    @Transactional
    InputUpdateProductStatusDto updateProductStatus(Product product){
        Product product1 = productService.updateProductStatus(product);
        return ShalMapper.INSTANCE.updateProductStatus(product1);
    }
}
