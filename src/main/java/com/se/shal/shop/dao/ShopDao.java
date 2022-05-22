package com.se.shal.shop.dao;

import com.se.shal.shop.entity.Shop;

import java.util.List;

public interface ShopDao {
    Shop save(Shop shop);
    Shop findById(Long id);
    List<Shop> getAllShop();
}
