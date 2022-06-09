package com.se.shal.util;

import com.se.shal.product.dto.*;
import com.se.shal.product.dto.input.*;
import com.se.shal.product.dto.query.QueryProductAttributeDto;
import com.se.shal.product.dto.query.QueryProductDto;
import com.se.shal.product.dto.query.QueryShipmentListDto;
import com.se.shal.product.dto.query.QueryVariationsDto;
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

    @Mapping(target = "shopId", source = "shop.id")
    InputUpdateProductStatusDto updateProductStatus(Product product);

    @Mapping(target = "shopId", source = "shop.id")
    InputProductDto saveProduct(Product product);

    @Mapping(target = "shopId", source = "shop.id")
    InputUpdateProductDto updateProduct(Product product);

    @Mapping(target = "productId", source = "product.id")
    InputSalesInformationDto saveSaleInformation(SalesInformation salesInformation);

    @Mapping(target = "productId", source = "product.id")
    InputUpdateSalesInformationDto updateSaleInformation(SalesInformation salesInformation);

    List<QueryProductDto> getQueryAllProductDto(List<Product> product);

    List<FilterProductDto> getFilterAllProductByCategory(List<Product> product);

    List<FilterProductDto> getFilterAllProductByStatus(List<Product> product);

    @Mapping(target = "shopId", source = "shop.id")
    FilterProductDto getFilterAllProductByCategory(Product product);

    @Mapping(target = "shopId", source = "shop.id")
    QueryProductDto getQueryAllProductDto(Product product);

    List<VariationsDto> saveVariations(List<Variations> variationsList);

    @Mapping(target = "productId", source = "product.id")
    VariationsDto saveVariations(Variations variationsList);

    List<InputUpdateVariationsDto> updateVariations(List<Variations> variationsList);

    @Mapping(target = "productId", source = "product.id")
    InputUpdateVariationsDto updateVariations(Variations variationsList);

    List<InputUpdateProductAttributeDto> updateProductAttributeDto(List<ProductAttribute> productAttributes);

    @Mapping(target = "productId", source = "product.id")
    InputUpdateProductAttributeDto updateProductAttributeDto(ProductAttribute productAttribute);

    @Mapping(target = "productId", source = "product.id")
    QueryVariationsDto getQueryVariationsDto(Variations variations);

    List<QueryVariationsDto> getQueryVariationsDto(List<Variations> variations);

    @Mappings({
            @Mapping(target = "shipments", ignore = true)
    })
    ShipmentList getShipmentList(InputShipmentList inputShipmentList);

    @Mappings({
            @Mapping(target = "shipments", ignore = true)
    })
    ShipmentList getUpdateShipmentList(UpdateShipmentList inputShipmentList);
    @Mapping(target = "attribute", ignore = true)
    List<ProductAttribute> getProductAttribute(List<InputProductAttributeDto> inputShipmentList);

    @Mapping(target = "shopId", source = "shop.id")
    ProductDto getProductDto(Product product);

    @Mapping(target = "productId", source = "product.id")
    SalesInformationDto getSalesInformationDto(SalesInformation salesInformation);


    @Mapping(target = "attribute", ignore = true)
    List<ProductAttributeDto> getProductAttributeDto(List<ProductAttribute> productAttributes);

    @Mapping(target = "attribute", ignore = true)
    List<QueryProductAttributeDto> getQueryProductAttributeDto(List<ProductAttribute> productAttributes);

    @Mapping(target = "productId", source = "product.id")
    QueryProductAttributeDto getQueryProductAttributeDto(ProductAttribute productAttributes);

    @Mapping(target = "productId", source = "product.id")
    ProductAttributeDto getProductAttributeDto(ProductAttribute productAttributes);

    @Mappings({
            @Mapping(target = "shipments",
                    expression = "java(shipmentList.getShipments().stream()" +
                            ".map(d -> d.getShipmentName().getShipmentName())" +
                            ".collect(Collectors.toList()))"),
            @Mapping(target = "productId", source = "product.id")
    })
    ShipmentListDto getShipmentListDto(ShipmentList shipmentList);

    @Mappings({
            @Mapping(target = "shipments",
                    expression = "java(shipmentList.getShipments().stream()" +
                            ".map(d -> d.getShipmentName().getShipmentName())" +
                            ".collect(Collectors.toList()))"),
            @Mapping(target = "productId", source = "product.id")
    })
    InputUpdateShipmentList getUpdateShipmentListDto(ShipmentList shipmentList);

    @Mappings({
            @Mapping(target = "shipments",
                    expression = "java(shipmentList.getShipments().stream()" +
                            ".map(d -> d.getShipmentName().getShipmentName())" +
                            ".collect(Collectors.toList()))"),
            @Mapping(target = "productId", source = "product.id")
    })
    QueryShipmentListDto getQueryShipmentListDto(ShipmentList shipmentList);

    @Mapping(target = "attributes", source = "attributes")
    CategoryDto getCategoryDto(Category category);
}
