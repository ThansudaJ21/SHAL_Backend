package com.se.shal.trading.service;

import com.se.shal.trading.dto.BidDto;
import com.se.shal.trading.entity.Bid;

import java.util.List;

public interface BidService {
    Bid addBid(BidDto bid);

    Bid getAuctionWinner(Long auctionId);

    List<Bid> findByUserIdOrShopIdOrAuctionId(Long userId, Long shopId, Long auctionId);

    void auctionList();

}
