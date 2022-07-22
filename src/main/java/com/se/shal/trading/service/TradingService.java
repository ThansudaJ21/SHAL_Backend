package com.se.shal.trading.service;

import com.se.shal.product.entity.Product;

public interface TradingService {
    Product buyProduct(Long userId,Long productId, Integer quantity);
}
