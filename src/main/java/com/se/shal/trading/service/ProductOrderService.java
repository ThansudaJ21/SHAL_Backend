package com.se.shal.trading.service;

import com.se.shal.trading.dto.ProductOrderInputDto;
import com.se.shal.trading.entity.Auction;
import com.se.shal.trading.entity.ProductOrder;

import java.util.List;

public interface ProductOrderService {
    ProductOrder buyProduct(ProductOrderInputDto productOrderInputDto);
    ProductOrder addToCart(ProductOrderInputDto productOrderInputDto);
    List<ProductOrder> findByUsersIdOrProductsIdOrShopId(Long userId, Long productId, Long shopId);

    List<ProductOrder> getAddToCartProduct(Long userId);
}
