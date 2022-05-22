package com.se.shal.shop.service;

import com.se.shal.shop.entity.Shop;

public interface ShopService {
    Shop registerShop(Shop shop);
    Shop getRegisterShop(Long id);
    Shop updateShopStatus(Shop shop);
}
