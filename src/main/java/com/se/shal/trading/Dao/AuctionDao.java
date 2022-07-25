package com.se.shal.trading.Dao;

import com.se.shal.trading.entity.Auction;

import java.util.List;

public interface AuctionDao {
    Auction findById(Long id);
    Auction save(Auction auction);
    List<Auction> findAll();
    List<Auction> findByProductId(Long productId);

    Long countByProductIdAndUserId(Long productId, Long userId);
}