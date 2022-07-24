package com.se.shal.trading.graphql;

import com.se.shal.trading.dto.AuctionQueryDto;
import com.se.shal.trading.dto.TradingHistoryQueryDto;
import com.se.shal.trading.entity.Auction;
import com.se.shal.trading.entity.TradingHistory;
import com.se.shal.trading.service.AuctionService;
import com.se.shal.trading.service.TradingService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class AuctionQueryQL implements GraphQLQueryResolver {
    @Autowired
    AuctionService auctionService;

    @Transactional
    public List<AuctionQueryDto> getAuctionByProductId(Long productId) {
        List<Auction> auctions =auctionService.getAuctionByProductId(productId);
        return ShalMapper.INSTANCE.getAuctionByProductId(auctions);
    }

}
