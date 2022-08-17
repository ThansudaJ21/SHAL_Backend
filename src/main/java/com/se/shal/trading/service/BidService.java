package com.se.shal.trading.service;

import com.se.shal.trading.dto.AuctionDto;
import com.se.shal.trading.dto.BidDto;
import com.se.shal.trading.entity.Auction;
import com.se.shal.trading.entity.Bid;

import java.util.List;

public interface BidService {
    Bid addBid( BidDto bid);
    Bid getCurrentBid(Long productId);
    List<Bid> findByUserIdOrShopId(Long userId, Long shopId);


}
