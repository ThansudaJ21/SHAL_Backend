package com.se.shal.trading.repository;

import com.se.shal.trading.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    List<ProductOrder> findProductOrderByShopId(Long shopId);
    List<ProductOrder> findProductOrderByProductsId(Long productId);

}
