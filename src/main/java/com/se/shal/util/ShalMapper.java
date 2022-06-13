package com.se.shal.util;

import com.se.shal.product.dto.*;
import com.se.shal.product.dto.input.*;
import com.se.shal.product.dto.query.*;
import com.se.shal.product.entity.*;
import com.se.shal.shop.dto.FailureReasonListDto;
import com.se.shal.shop.dto.QueryFailureReasonDto;
import com.se.shal.shop.dto.QueryFailureReasonListDto;
import com.se.shal.shop.dto.ShopDto;
import com.se.shal.shop.entity.FailureReason;
import com.se.shal.shop.entity.FailureReasonList;
import com.se.shal.shop.entity.Shop;
import org.mapstruct.InheritInverseConfiguration;
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

    @Mappings({
            @Mapping(target = "shipments",
                    expression = "java(product.getShipments().stream()" +
                            ".map(d -> d.getShipmentName().getShipmentName())" +
                            ".collect(Collectors.toList()))"),
            @Mapping(target = "shopId", source = "shop.id")
    })
    InputUpdateProductStatusDto updateProductStatus(Product product);

    @Mappings({
            @Mapping(target = "shipments", ignore = true)
    })
    Product saveProduct(InputProductDto product);

    @Mappings({
            @Mapping(target = "shipments", ignore = true)
    })
    Product updateProduct(InputUpdateProductDto product);

    @Mappings({
            @Mapping(target = "shipments",
                    expression = "java(product.getShipments().stream()" +
                            ".map(d -> d.getShipmentName().getShipmentName())" +
                            ".collect(Collectors.toList()))"),
            @Mapping(target = "shopId", source = "shop.id")
    })
    InputUpdateProductDto updateProduct(Product product);

    @Mappings({
            @Mapping(target = "shipments",
                    expression = "java(product.getShipments().stream()" +
                            ".map(d -> d.getShipmentName().getShipmentName())" +
                            ".collect(Collectors.toList()))"),
            @Mapping(target = "shopId", source = "shop.id")
    })
    ProductDto saveProductDto(Product product);
    @Mappings({
            @Mapping(target = "shipments",
                    expression = "java(product.getShipments().stream()" +
                            ".map(d -> d.getShipmentName().getShipmentName())" +
                            ".collect(Collectors.toList()))"),
            @Mapping(target = "shopId", source = "shop.id")
    })
    QueryProductDto getQueryAllProductDto(Product product);

    List<QueryProductDto> getQueryAllProductDto(List<Product> product);

    List<FilterProductDto> getFilterAllProductByCategory(List<Product> product);

    List<FilterProductDto> getFilterAllProductByStatus(List<Product> product);

    @Mappings({
            @Mapping(target = "shipments",
                    expression = "java(product.getShipments().stream()" +
                            ".map(d -> d.getShipmentName().getShipmentName())" +
                            ".collect(Collectors.toList()))"),
            @Mapping(target = "shopId", source = "shop.id")
    })
    FilterProductDto getFilterAllProductByCategory(Product product);

//    @Mapping(target = "shopId" , ignore = true)
    FailureReasonListDto saveFailureReasonList(FailureReasonList failureReasonLists);

    List<FailureReasonListDto> getFailureReasonList(List<FailureReasonList> failureReasonLists);

    List<QueryFailureReasonDto> getAllFailureReasonList(List<FailureReason> failureReasonLists);

//    @Mapping(target = "shopId", ignore = true)
    QueryFailureReasonListDto getFailureReasonListByShopId(FailureReasonList failureReasonLists);

    List<QueryFailureReasonListDto> getFailureReasonListByShopId(List<FailureReasonList> failureReasonLists);

    List<QueryVariationsDto> getQueryVariationsDto(List<Variations> variations);


    @Mappings({
            @Mapping(target = "shipments",
                    expression = "java(product.getShipments().stream()" +
                            ".map(d -> d.getShipmentName().getShipmentName())" +
                            ".collect(Collectors.toList()))"),
            @Mapping(target = "shopId", source = "shop.id")
    })
    ProductQuery getProductDto(Product product);

    @Mapping(target = "attributes", source = "attributes")
    CategoryDto getCategoryDto(Category category);
}
