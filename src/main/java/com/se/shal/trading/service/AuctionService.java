package com.se.shal.trading.service;

import com.se.shal.trading.dto.AuctionDto;
import com.se.shal.trading.entity.Auction;

import java.util.List;

public interface AuctionService {
    Auction addAuction(AuctionDto auction);
//    Auction checkCurrentBid(Long productId, Double bidAmount);

//    List<Auction> findByUserIdOrProductIdOrShopId(Long userId,Long productId,Long shopId);
}
