package com.se.shal.trading.Dao;

import com.se.shal.trading.entity.TradingHistory;

import java.util.List;

public interface TradingHistoryDao {
    TradingHistory save(TradingHistory tradingHistory);
    List<TradingHistory> getTradingHistoryByProductId(Long id);
}
