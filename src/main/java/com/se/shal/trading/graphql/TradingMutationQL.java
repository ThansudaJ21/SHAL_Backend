package com.se.shal.trading.graphql;

import com.se.shal.product.dto.ProductDto;
import com.se.shal.product.entity.Product;
import com.se.shal.trading.service.TradingService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class TradingMutationQL implements GraphQLMutationResolver {
    @Autowired
    TradingService tradingService;

    @Transactional
     public ProductDto buyProduct(Long userId, Long productId, Integer quantity) {
        Product product = tradingService.buyProduct(userId, productId,quantity);
        return ShalMapper.INSTANCE.saveProductDto(product);
    }
}
