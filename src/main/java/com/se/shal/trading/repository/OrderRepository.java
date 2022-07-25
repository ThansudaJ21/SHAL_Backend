package com.se.shal.trading.repository;

import com.se.shal.trading.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<ProductOrder, Long> {
    List<ProductOrder> findOrderByProductsId(Long productId);
}
