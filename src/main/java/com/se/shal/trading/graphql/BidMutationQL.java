package com.se.shal.trading.graphql;

import com.se.shal.trading.dto.BidDto;
import com.se.shal.trading.entity.Bid;
import com.se.shal.trading.service.BidService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BidMutationQL implements GraphQLMutationResolver {
    @Autowired
    BidService bidService;

    @Transactional
    public BidDto addBid(BidDto bid) {
        Bid newBid = bidService.addBid(bid);
        return ShalMapper.INSTANCE.getBidDto(newBid);
    }
}
