package com.se.shal.shop.dao;

import com.se.shal.shop.entity.Shop;
import com.se.shal.shop.graphql.entity.ShopQueryFilterByShopName;
import com.se.shal.shop.graphql.entity.ShopQueryFilterByShopStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ShopDao {
    Shop save(Shop shop);
    Shop findById(Long id);
    List<Shop> getAllShop();
    Page<Shop> getShopByFilterByShopNameOrShopStatus(ShopQueryFilterByShopName filter, PageRequest pageRequest);
}
