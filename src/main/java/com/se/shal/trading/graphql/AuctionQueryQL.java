package com.se.shal.trading.graphql;

import com.se.shal.trading.dto.AuctionQueryDto;
import com.se.shal.trading.entity.Auction;
import com.se.shal.trading.service.BidService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AuctionQueryQL implements GraphQLQueryResolver {
    @Autowired
    BidService bidService;
}
