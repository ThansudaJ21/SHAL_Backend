package com.se.shal.trading.graphql;

import com.se.shal.trading.dto.AuctionDto;
import com.se.shal.trading.entity.Auction;
import com.se.shal.trading.service.AuctionService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class AuctionMutationQL implements GraphQLMutationResolver {
    @Autowired
    AuctionService auctionService;

    @Transactional
    public AuctionDto auction(AuctionDto auction) {
        Auction newAuction = auctionService.auction(auction);
        return ShalMapper.INSTANCE.saveAuction(newAuction);
    }
}
