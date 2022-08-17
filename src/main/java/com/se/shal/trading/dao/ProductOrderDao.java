package com.se.shal.trading.dao;

import com.se.shal.trading.entity.ProductOrder;

import java.util.List;

public interface ProductOrderDao {
    ProductOrder save(ProductOrder productOrder);
    List<ProductOrder> findByUsersIdOrProductsIdOrShopId(Long userId, Long productId, Long shopId);
    List<ProductOrder> findByUsersId(Long userId);
}
