package com.se.shal.trading.graphql;

import com.se.shal.trading.dto.TradingHistoryQueryDto;
import com.se.shal.trading.entity.TradingHistory;
import com.se.shal.trading.service.TradingService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class TradingQueryQL implements GraphQLQueryResolver {
    @Autowired
    TradingService tradingService;

    @Transactional
    public List<TradingHistoryQueryDto> getTradingHistoryByProductId(Long productId){
        List<TradingHistory> tradingHistories = tradingService.getTradingHistoryByProductId(productId);
        return ShalMapper.INSTANCE.getTradingHistoryByProductId(tradingHistories);
    }

}
