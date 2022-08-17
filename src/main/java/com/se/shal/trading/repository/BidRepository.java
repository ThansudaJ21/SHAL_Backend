package com.se.shal.trading.repository;

import com.se.shal.trading.entity.Auction;
import com.se.shal.trading.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {
    Long countByUserId(Long userId);
    List<Bid> findByAuctionId(Long auctionId);

    List<Bid> findBidsByUserIdOrShopId(Long userId,Long shopId);
}
