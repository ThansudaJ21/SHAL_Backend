package com.se.shal.trading.graphql;

import com.se.shal.trading.dto.BidQueryDto;
import com.se.shal.trading.entity.Bid;
import com.se.shal.trading.service.BidService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BidQueryQL implements GraphQLQueryResolver {
    @Autowired
    BidService bidService;

    @Transactional
    public BidQueryDto getCurrentBid(Long productId) {
        Bid getCurrentBid = bidService.getCurrentBid(productId);
        return ShalMapper.INSTANCE.getCurrentBid(getCurrentBid);
    }
}
