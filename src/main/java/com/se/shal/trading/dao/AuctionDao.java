package com.se.shal.trading.dao;

import com.se.shal.trading.entity.Auction;

import java.time.LocalDateTime;
import java.util.List;

public interface AuctionDao {
    Auction findById(Long id);

    Auction findByProductId(Long productId);

    Auction save(Auction auction);

    List<Auction> findByMaxBiddingId(Long maxBiddingId);

    List<Auction> findAll();

    List<Auction> findEndAuctionWithoutNotification(LocalDateTime currentDate);

    List<Auction> getNonEndBidTimeAuction();

    List<Auction> findNextAuction(LocalDateTime currentDate);
}
