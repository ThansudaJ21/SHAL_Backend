package com.se.shal.trading.service;

import com.se.shal.trading.dto.AuctionDto;
import com.se.shal.trading.entity.Auction;

import java.util.List;

public interface AuctionService {
    Auction auction(AuctionDto auction);
//    List<Auction> checkBidAmount(Long productId, Double bidAmount);

    List<Auction> findByUserIdOrProductIdOrShopId(Long userId,Long productId,Long shopId);
}
