package com.se.shal.trading.dao;

import com.se.shal.trading.entity.Bid;

import java.util.List;

public interface BidDao {
    Long countByUserIdAndAuctionId(Long userId, Long auctionId);

    Bid save(Bid bid);

    List<Bid> findBidsByUserIdOrShopIdOrAuctionId(Long userId, Long shopId, Long auctionId);

    List<Bid> findByAuctionId(Long auctionId);
}
