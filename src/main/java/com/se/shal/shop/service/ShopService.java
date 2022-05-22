package com.se.shal.shop.service;

import com.se.shal.shop.entity.Shop;

import java.util.List;

public interface ShopService {
    Shop registerShop(Shop shop);
    Shop getRegisterShop(Long id);
    Shop updateShopStatus(Shop shop);
    List<Shop> getAllShop();
}
