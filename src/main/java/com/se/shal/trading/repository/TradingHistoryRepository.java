package com.se.shal.trading.repository;

import com.se.shal.trading.entity.TradingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradingHistoryRepository extends JpaRepository<TradingHistory, Long> {
    List<TradingHistory> findTradingHistoryByProductsId(Long productId);
}
