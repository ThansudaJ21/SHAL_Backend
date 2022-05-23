package com.se.shal.util;

import com.se.shal.shop.dto.ShopDto;
import com.se.shal.shop.entity.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface ShalMapper {
    ShalMapper INSTANCE = Mappers.getMapper(ShalMapper.class);

    ShopDto registerShop(Shop Shop);
    ShopDto updateShopStatus(Shop shop);
}
