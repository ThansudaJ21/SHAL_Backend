package com.se.shal.trading.service;

import com.se.shal.trading.dto.OrderInputDto;
import com.se.shal.trading.entity.ProductOrder;

import java.util.List;

public interface OrderService {
    ProductOrder buyProduct(OrderInputDto orderInputDto);
    List<ProductOrder> getOrderByProductId(Long productId);
}