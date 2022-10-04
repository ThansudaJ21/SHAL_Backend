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
        List<ProductOrder> productOrderByShopId = productOrderService.findByUsersIdOrProductsIdOrShopId(userId, productId, shopId);
        return ShalMapper.INSTANCE.getProductOrder(productOrderByShopId);
    }

    @Transactional
    public List<ProductOrderQueryDto> getAddToCartProduct(Long userId) {
        List<ProductOrder> addToCartProduct = productOrderService.getAddToCartProduct(userId);
        return ShalMapper.INSTANCE.getProductOrder(addToCartProduct);
    }

    @Transactional
    public List<ProductOrderQueryDto> findByShopIdAndPaymentStatus(Long shopId, String paymentStatus) {
        List<ProductOrder> productOrderByShopId = productOrderService.findByShopIdAndPaymentStatus(shopId, paymentStatus);
        return ShalMapper.INSTANCE.getProductOrder(productOrderByShopId);
    }
}
