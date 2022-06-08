package com.se.shal.shop.service;

import com.se.shal.product.entity.Product;
import com.se.shal.shop.entity.Shop;
import com.se.shal.shop.graphql.entity.ShopQueryFilterByShopName;
import com.se.shal.shop.graphql.entity.ShopQueryFilterByShopStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ShopService {
    Shop registerShop(Shop shop);
    Shop getRegisterShop(Long id);
    Shop updateShopStatus(Shop shop);
    List<Shop> getAllShop();
    Page<Shop> findShopByFilterByShopNameOrShopStatus(ShopQueryFilterByShopName filter, PageRequest pageRequest);
    List<Shop> shopFilterByStatus(String status);

}
