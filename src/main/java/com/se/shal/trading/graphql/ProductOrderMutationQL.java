package com.se.shal.trading.graphql;

import com.se.shal.trading.dto.ProductOrderInputDto;
import com.se.shal.trading.entity.ProductOrder;
import com.se.shal.trading.service.ProductOrderService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ProductOrderMutationQL implements GraphQLMutationResolver {
    @Autowired
    ProductOrderService productOrderService;

    @Transactional
    public ProductOrderInputDto buyProduct(ProductOrderInputDto productOrderInputDto) {
        ProductOrder newProductOrder = productOrderService.buyProduct(productOrderInputDto);
        return ShalMapper.INSTANCE.buyProduct(newProductOrder);
    }

    @Transactional
    public ProductOrderInputDto addToCart(ProductOrderInputDto productOrderInputDto) {
        ProductOrder addToCartOrder = productOrderService.addToCart(productOrderInputDto);
        return ShalMapper.INSTANCE.buyProduct(addToCartOrder);
    }

    @Transactional
    public ProductOrderInputDto updatePaymentStatusToPaid(Long productOrderId, String slipPaymentUrl) {
        ProductOrder addToCartOrder = productOrderService.updatePaymentStatusToPaid(productOrderId, slipPaymentUrl);
        return ShalMapper.INSTANCE.buyProduct(addToCartOrder);
    }

    @Transactional
    public ProductOrderInputDto updatePaymentStatusToDelivered(Long productOrderId, String trackingNumber) {
        ProductOrder addToCartOrder = productOrderService.updatePaymentStatusToDelivered(productOrderId, trackingNumber);
        return ShalMapper.INSTANCE.buyProduct(addToCartOrder);
    }

}
