package com.se.shal.trading.graphql;

import com.se.shal.trading.dto.ProductOrderQueryDto;
import com.se.shal.trading.entity.ProductOrder;
import com.se.shal.trading.service.ProductOrderService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class ProductOrderQueryQL implements GraphQLQueryResolver {
    @Autowired
    ProductOrderService productOrderService;

    @Transactional
    public List<ProductOrderQueryDto> findProductOrderByUserIdOrProductIdOrShopId(Long userId, Long productId, Long shopId) {
        List<ProductOrder> productOrderList = productOrderService.findByUsersIdOrProductsIdOrShopId(userId, productId, shopId);
        return ShalMapper.INSTANCE.getProductOrder(productOrderList);
    }

    @Transactional
    public List<ProductOrderQueryDto> getAddToCartProduct(Long userId) {
        List<ProductOrder> productOrderList = productOrderService.getAddToCartProduct(userId);
        return ShalMapper.INSTANCE.getProductOrder(productOrderList);
    }

    @Transactional
    public List<ProductOrderQueryDto> findByShopIdAndPaymentStatus(Long shopId, String paymentStatus) {
        List<ProductOrder> productOrderList = productOrderService.findByShopIdAndPaymentStatus(shopId, paymentStatus);
        return ShalMapper.INSTANCE.getProductOrder(productOrderList);
    }

    @Transactional
    public ProductOrderQueryDto getProductOrderById(Long productOrderId) {
        ProductOrder productOrder = productOrderService.getProductOrderById(productOrderId);
        return ShalMapper.INSTANCE.getProductOrder(productOrder);
    }
}
