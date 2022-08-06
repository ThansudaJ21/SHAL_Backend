package com.se.shal.trading.Dao;

import com.se.shal.trading.entity.Bid;

import java.util.List;

public interface BidDao {
    Long countByUserId(Long userId);
    Bid saveBid(Bid bid);
    List<Bid> findByProductId(Long productId);
    Bid findByProductIdAndBidAmountIsLessThan(Long productId, Double bidAmount);
}
