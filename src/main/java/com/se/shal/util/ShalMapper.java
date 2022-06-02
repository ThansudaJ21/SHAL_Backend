package com.se.shal.util;

import com.se.shal.product.dto.*;
import com.se.shal.product.entity.*;
import com.se.shal.shop.dto.ShopDto;
import com.se.shal.shop.entity.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface ShalMapper {
    ShalMapper INSTANCE = Mappers.getMapper(ShalMapper.class);

    ShopDto registerShop(Shop Shop);
    ShopDto updateShopStatus(Shop shop);

    @Mapping(target = "shopId",  source = "shop.id")
    InputProductDto saveProduct(Product product);

    @Mapping(target = "productId",  source = "product.id")
    InputSalesInformationDto saveSaleInformation(SalesInformation salesInformation);

    @Mapping(target = "productId",  source = "product.id")
    List<VariationsDto> saveVariations(List<Variations> variationsList) ;

    @Mapping(target = "shipments", ignore = true)
    ShipmentList getShipmentList(InputShipmentList inputShipmentList);

    @Mapping(target = "attribute", ignore = true)
    List<ProductAttribute> getProductAttribute(List<InputProductAttributeDto> inputShipmentList);

    ProductDto getProductDto(Product product);
    
    @Mapping(target = "productId",  source = "product.id")
    SalesInformationDto getSalesInformationDto(SalesInformation salesInformation);

    @Mappings({
            @Mapping(target = "productId",  source = "product.id"),
            @Mapping(target = "attribute", ignore = true)
    })
    List<ProductAttributeDto> getProductAttributeDto(List<ProductAttribute> productAttributes);

    @Mappings({
            @Mapping(target = "shipments",
                    expression = "java(shipmentList.getShipments().stream()" +
                            ".map(d -> d.getShipmentName().getShipmentName())" +
                            ".collect(Collectors.toList()))"),
            @Mapping(target = "ProductId", source = "product.id"),
    })
    ShipmentListDto getShipmentListDto(ShipmentList shipmentList);

    @Mapping(target = "attributes", source = "attributes")
    CategoryDto getCategoryDto(Category category);
}
