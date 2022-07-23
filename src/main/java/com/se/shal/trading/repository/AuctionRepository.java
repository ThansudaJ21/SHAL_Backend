package com.se.shal.trading.repository;

import com.se.shal.trading.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
    List<Auction> findByProductId(Long productId);
}
