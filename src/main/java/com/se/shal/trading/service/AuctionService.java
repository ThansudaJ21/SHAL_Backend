package com.se.shal.trading.service;

import com.se.shal.trading.dto.AuctionDto;
import com.se.shal.trading.entity.Auction;

import java.util.List;

public interface AuctionService {
    Auction saveAuction(AuctionDto auction);

    List<Auction> checkBidAmount(Long productId, Double bidAmount);

    List<Auction> getAuctionByProductId(Long productId);

}
