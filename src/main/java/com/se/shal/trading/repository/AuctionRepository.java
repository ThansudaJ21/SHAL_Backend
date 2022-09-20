package com.se.shal.trading.repository;

import com.se.shal.trading.entity.Auction;
import com.se.shal.trading.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
    Auction findByProductId(Long productId);
    List<Auction> findByEndBiddingTimeBeforeAndIsNotification(LocalDateTime currentTime, Boolean isNotification);

    List<Auction> findByMaxBiddingId(Long maxBiddingId);
    List<Auction> findByEndBiddingTimeIsNull();
}
