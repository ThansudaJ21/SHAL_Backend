package com.se.shal.trading.service;

import com.se.shal.trading.dto.ProductOrderInputDto;
import com.se.shal.trading.entity.Auction;
import com.se.shal.trading.entity.ProductOrder;
import com.se.shal.trading.entity.enumeration.PaymentStatus;

import java.util.List;

public interface ProductOrderService {
    ProductOrder buyProduct(ProductOrderInputDto productOrderInputDto);

    ProductOrder addToCart(ProductOrderInputDto productOrderInputDto);

    List<ProductOrder> findByUsersIdOrProductsIdOrShopId(Long userId, Long productId, Long shopId);

    List<ProductOrder> getAddToCartProduct(Long userId);

    List<ProductOrder> findByShopIdAndPaymentStatus(Long shopId, String paymentStatus);

    ProductOrder updatePaymentStatusToPaid(Long productOrderId);

    ProductOrder updatePaymentStatusToDelivered(Long productOrderId, String trackingNumber);

    ProductOrder updatePaymentStatusToPendingToConfirm(Long productOrderId, String slipPaymentUrl);

    ProductOrder getProductOrderById(Long productOrderId);

    ProductOrder deleteProductOrderById(Long productOrderId);

    ProductOrder cancelProductOrderById(Long productOrderId);
}
