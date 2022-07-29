package com.se.shal.trading.graphql;

import com.se.shal.trading.service.BidService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuctionQueryQL implements GraphQLQueryResolver {
    @Autowired
    BidService bidService;

//    @Transactional
//    public List<AuctionQueryDto> findAuctionByUserIdOrProductIdOrShopId(Long userId,Long productId,Long shopId) {
//        List<Auction> auctions =auctionService.findByUserIdOrProductIdOrShopId(userId,productId,shopId);
//        return ShalMapper.INSTANCE.getAuction(auctions);
//    }
}
