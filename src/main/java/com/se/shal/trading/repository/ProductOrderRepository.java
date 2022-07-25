package com.se.shal.trading.repository;

import com.se.shal.trading.entity.Auction;
import com.se.shal.trading.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    List<ProductOrder> findByUsersIdOrProductsIdOrShopId(Long userId, Long productId, Long shopId);
    List<ProductOrder> findByUsersId(Long userId);
}
