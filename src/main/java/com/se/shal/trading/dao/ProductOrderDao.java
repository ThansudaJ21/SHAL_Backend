package com.se.shal.trading.dao;

import com.se.shal.trading.entity.ProductOrder;
import com.se.shal.trading.entity.enumeration.OrderStatus;
import com.se.shal.trading.entity.enumeration.PaymentStatus;

import java.util.List;

public interface ProductOrderDao {
    ProductOrder save(ProductOrder productOrder);

    List<ProductOrder> findByUsersIdOrProductsIdOrShopId(Long userId, Long productId, Long shopId);

    List<ProductOrder> findByUsersId(Long userId);

    List<ProductOrder> findAll();

    ProductOrder findById(Long id);

    List<ProductOrder> findByShopIdAndPaymentStatus(Long shopId, PaymentStatus paymentStatus);
}
