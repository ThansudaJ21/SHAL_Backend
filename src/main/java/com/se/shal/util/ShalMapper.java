package com.se.shal.util;

import com.se.shal.product.dto.*;
import com.se.shal.product.entity.Category;
import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.SalesInformation;
import com.se.shal.product.entity.Variations;
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

    @Mapping(target = "ShopId",  source = "shop.id")
    ProductDto saveProduct(Product product);

    @Mapping(target = "productId",  source = "product.id")
    SalesInformationDto saveSaleInformation(SalesInformation salesInformation);

    @Mapping(target = "productId",  source = "product.id")
    List<VariationsDto> saveVariations(List<Variations> variationsList) ;

//    @Mapping(target = "shipments", ignore = true)
//    Product getProduct(InputProductDto inputDto);

    @Mapping(target = "attributes", source = "attributes")
    CategoryDto getCategoryDto(Category category);
}
