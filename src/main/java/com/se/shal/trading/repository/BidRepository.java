package com.se.shal.trading.repository;

import com.se.shal.trading.entity.Auction;
import com.se.shal.trading.entity.Bid;
import com.se.shal.trading.entity.enumeration.AuctionResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {
    Long countByUserIdAndAuctionId(Long userId, Long auctionId);

    List<Bid> findByAuctionId(Long auctionId);

    Bid findByAuctionIdAndAuctionResult(Long auctionId, AuctionResult auctionResult);

    List<Bid> findByUser(Long auctionId);

    List<Bid> findByUserIdOrShopIdOrAuctionIdOrderByLocalDateTimeDesc(Long userId, Long shopId, Long auctionId);

}
