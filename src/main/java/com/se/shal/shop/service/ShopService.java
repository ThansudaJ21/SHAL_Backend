package com.se.shal.shop.service;

import com.se.shal.shop.entity.FailureReason;
import com.se.shal.shop.entity.FailureReasonList;
import com.se.shal.shop.entity.Shop;
import com.se.shal.shop.graphql.entity.ShopQueryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ShopService {
    Shop registerShop(Shop shop);
    Shop getRegisterShop(Long id);
    Shop updateShopStatus(Shop shop);
    List<Shop> getAllShop();
    Page<Shop> findShopByFilterByShopNameOrShopStatus(ShopQueryFilter filter, PageRequest pageRequest);
    List<Shop> shopFilterByStatus(String status);
    List<FailureReasonList> saveFailureReason(Long shopId, List<FailureReasonList> failureReason);
    List<FailureReason> getFailureReason();
    List<FailureReasonList> getFailureReasonByShopId(Long shopId);
}
