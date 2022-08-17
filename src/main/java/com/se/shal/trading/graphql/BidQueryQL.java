package com.se.shal.trading.graphql;

import com.se.shal.trading.dto.BidQueryDto;
import com.se.shal.trading.entity.Auction;
import com.se.shal.trading.entity.Bid;
import com.se.shal.trading.service.BidService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BidQueryQL implements GraphQLQueryResolver {
    @Autowired
    BidService bidService;

    @Transactional
    public BidQueryDto getAuctionWinner(Long auctionId) {
        Bid getCurrentBid = bidService.getAuctionWinner(auctionId);
        return ShalMapper.INSTANCE.getBidQueryDto(getCurrentBid);
    }

    @Transactional
    public List<BidQueryDto> findAuctionByUserIdOrAuctionIdOrShopId(Long userId, Long shopId, Long auctionId) {
        List<Bid> bids = bidService.findByUserIdOrShopIdOrAuctionId(userId, shopId, auctionId);
        return ShalMapper.INSTANCE.getBidQueryDto(bids);
    }

}
