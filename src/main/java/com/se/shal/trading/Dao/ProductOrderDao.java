package com.se.shal.trading.Dao;

import com.se.shal.trading.entity.ProductOrder;

import java.util.List;

public interface ProductOrderDao {
    ProductOrder save(ProductOrder productOrder);
    List<ProductOrder> findProductOrderByShopId(Long shopId);
    List<ProductOrder> findProductOrderByProductsId(Long productId);
}
