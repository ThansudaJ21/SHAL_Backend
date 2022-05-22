package com.se.shal.shop.dao;

import com.se.shal.shop.entity.Shop;

public interface ShopDao {
    Shop save(Shop shop);
    Shop getRegisterShop(Long id);
    Shop findById(Long id);
}
