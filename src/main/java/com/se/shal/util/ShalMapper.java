package com.se.shal.util;

import com.se.shal.product.dto.*;
import com.se.shal.product.dto.input.*;
import com.se.shal.product.dto.query.*;
import com.se.shal.product.entity.*;
import com.se.shal.security.entity.User;
import com.se.shal.security.entity.UserDto;
import com.se.shal.shop.dto.FailureReasonListDto;
import com.se.shal.shop.dto.QueryFailureReasonDto;
import com.se.shal.shop.dto.QueryFailureReasonListDto;
import com.se.shal.shop.dto.ShopDto;
import com.se.shal.shop.entity.FailureReason;
import com.se.shal.shop.entity.FailureReasonList;
import com.se.shal.shop.entity.Shop;
import com.se.shal.trading.dto.*;
import com.se.shal.trading.entity.Auction;
import com.se.shal.trading.entity.Bid;
import com.se.shal.trading.entity.ProductOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface ShalMapper {
    ShalMapper INSTANCE = Mappers.getMapper(ShalMapper.class);


    @Mapping(target = "userId", source = "user.id")
    ShopDto registerShop(Shop Shop);

    @Mapping(target = "userId", source = "user.id")
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
            @Mapping(target = "shopId", source = "shop.id"),
    })
    InputUpdateProductDto updateProduct(Product product);

    @Mappings({
            @Mapping(target = "shipments",
                    expression = "java(product.getShipments().stream()" +
                            ".map(d -> d.getShipmentName().getShipmentName())" +
                            ".collect(Collectors.toList()))"),
            @Mapping(target = "shopId", source = "shop.id")
    })
    InputProductDto saveProductTest(Product product);

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

    FailureReasonListDto saveFailureReasonList(FailureReasonList failureReasonLists);

    List<FailureReasonListDto> getFailureReasonList(List<FailureReasonList> failureReasonLists);

    List<QueryFailureReasonDto> getAllFailureReasonList(List<FailureReason> failureReasonLists);

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

    UserDto getUserDto(User user);

    List<ProductOrderQueryDto> getProductOrder(List<ProductOrder> productProductOrderList);

    @Mappings({
            @Mapping(target = "users", source = "users"),
            @Mapping(target = "products", source = "products"),
            @Mapping(target = "shop", source = "shop.id")
    })
    ProductOrderQueryDto getProductOrder(ProductOrder tradingHistories);

    @Mappings({
            @Mapping(target = "userId", source = "user.id"),
            @Mapping(target = "productId", source = "product.id"),
            @Mapping(target = "shopId", source = "shop.id")
    })
    BidDto getBidDto(Bid bid);

    @Mappings({
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "product", source = "product"),
            @Mapping(target = "shop", source = "shop")
    })
    BidQueryDto getBidQueryDto(Bid bid);

    List<BidQueryDto> getBidQueryDto(List<Bid> bid);

    @Mappings({
            @Mapping(target = "users", source = "users.id"),
            @Mapping(target = "products", source = "products.id"),
            @Mapping(target = "variationsList",
                    expression = "java(productOrder.getVariationsList().stream()" +
                            ".map(variationsList -> variationsList.getId())" +
                            ".collect(Collectors.toList()))"),
            @Mapping(target = "optionsList",
                    expression = "java(productOrder.getOptionsList().stream()" +
                            ".map(optionsList -> optionsList.getId())" +
                            ".collect(Collectors.toList()))"),
            @Mapping(target = "shop", source = "shop.id")
    })
    ProductOrderInputDto buyProduct(ProductOrder productOrder);
}
