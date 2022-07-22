package com.se.shal.trading.Dao;

import com.se.shal.trading.entity.TradingHistory;
import com.se.shal.trading.repository.TradingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TradingHistoryDaoImpl implements TradingHistoryDao{

    @Autowired
    TradingHistoryRepository tradingHistoryRepository;

    @Override
    public TradingHistory save(TradingHistory tradingHistory) {
        return tradingHistoryRepository.save(tradingHistory);
    }
}
