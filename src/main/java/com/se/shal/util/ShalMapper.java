package com.se.shal.util;

import com.se.shal.product.dto.CategoryDto;
import com.se.shal.product.dto.InputProductDto;
import com.se.shal.product.dto.ProductDto;
import com.se.shal.product.entity.Category;
import com.se.shal.product.entity.Product;
import com.se.shal.shop.dto.ShopDto;
import com.se.shal.shop.entity.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface ShalMapper {
    ShalMapper INSTANCE = Mappers.getMapper(ShalMapper.class);

    ShopDto registerShop(Shop Shop);
    ShopDto updateShopStatus(Shop shop);

    @Mapping(target = "shipments",
            expression = "java(product.getShipments().stream()" +
                    ".map(d -> d.getShipmentName().getShipmentName())" +
                    ".collect(Collectors.toList()))")
    ProductDto saveProduct(Product product);


    @Mapping(target = "shipments", ignore = true)
    Product getProduct(InputProductDto inputDto);

    @Mapping(target = "attributes", source = "attributes")
    CategoryDto getCategoryDto(Category category);
}
