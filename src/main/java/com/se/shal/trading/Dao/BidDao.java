package com.se.shal.trading.Dao;

import com.se.shal.trading.entity.Bid;

public interface BidDao {
    Long countByUserId(Long userId);
    Bid saveBid(Bid bid);
}
