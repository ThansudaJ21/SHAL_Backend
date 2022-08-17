package com.se.shal.trading.dao;

import com.se.shal.trading.entity.Bid;

import java.util.List;

public interface BidDao {
    Long countByUserId(Long userId);

    Bid save(Bid bid);

    List<Bid> findByUserIdOrShopId(Long userId, Long shopId);

    List<Bid> findByAuctionId(Long auctionId);
}
