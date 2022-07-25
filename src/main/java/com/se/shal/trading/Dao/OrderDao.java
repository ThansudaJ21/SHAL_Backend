package com.se.shal.trading.Dao;

import com.se.shal.trading.entity.ProductOrder;

import java.util.List;

public interface OrderDao {
    ProductOrder save(ProductOrder productOrder);
    List<ProductOrder> getOrderByProductId(Long id);
}
