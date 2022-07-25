package com.se.shal.trading.graphql;

import com.se.shal.trading.dto.OrderInputDto;
import com.se.shal.trading.entity.ProductOrder;
import com.se.shal.trading.service.OrderService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class OrderMutationQL implements GraphQLMutationResolver {
    @Autowired
    OrderService orderService;

    @Transactional
     public OrderInputDto buyProduct(OrderInputDto orderInputDto) {
        ProductOrder newProductProductOrder = orderService.buyProduct(orderInputDto);
        return ShalMapper.INSTANCE.buyProduct(newProductProductOrder);
    }
}
