package com.se.shal.trading.service;

import com.se.shal.product.entity.Product;
import com.se.shal.trading.entity.TradingHistory;

import java.util.List;

public interface TradingService {
    Product buyProduct(Long userId,Long productId, Integer quantity);
    List<TradingHistory> getTradingHistoryByProductId(Long productId);
}
