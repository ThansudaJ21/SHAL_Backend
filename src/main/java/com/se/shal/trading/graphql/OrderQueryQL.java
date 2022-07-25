package com.se.shal.trading.graphql;

import com.se.shal.trading.dto.OrderQueryDto;
import com.se.shal.trading.entity.ProductOrder;
import com.se.shal.trading.service.OrderService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class OrderQueryQL implements GraphQLQueryResolver {
    @Autowired
    OrderService orderService;

    @Transactional
    public List<OrderQueryDto> getOrderByProductId(Long productId){
        List<ProductOrder> tradingHistories = orderService.getOrderByProductId(productId);
        return ShalMapper.INSTANCE.getOrderByProductId(tradingHistories);
    }

}
