package com.se.shal.shop.graphql;

import com.se.shal.product.entity.enumeration.CategoryName;
import com.se.shal.shop.dto.QueryFailureReasonDto;
import com.se.shal.shop.dto.ShopQueryResultDto;
import com.se.shal.shop.entity.FailureReason;
import com.se.shal.shop.entity.PromptPayType;
import com.se.shal.shop.entity.Shop;
import com.se.shal.shop.graphql.entity.ShopQueryFilter;
import com.se.shal.shop.service.ShopService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;


import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShopQueryQL implements GraphQLQueryResolver {

    @Autowired
    ShopService shopService;


    @Transactional
    public ShopQueryResultDto getRegisterShop(Long id) {
        Shop shop = shopService.getRegisterShop(id);
        return ShalMapper.INSTANCE.getShopQueryDto(shop);
    }


    @Transactional
    Page<Shop> shopQueryFilter(ShopQueryFilter filter, int pageNo, int pageSize) {
        return shopService.findShopByFilterByShopNameOrShopStatus(filter, PageRequest.of(pageNo, pageSize));
    }


    @Transactional
    List<QueryFailureReasonDto> getFailureReason() {
        List<FailureReason> failureReasons = shopService.getFailureReason();
        return ShalMapper.INSTANCE.getAllFailureReasonList(failureReasons);
    }

    @Transactional
    public ShopQueryResultDto getShopByUserId(Long userId) {
        Shop shopByUserId = shopService.findByUserId(userId);
        return ShalMapper.INSTANCE.getShopQueryDto(shopByUserId);
    }

    List<PromptPayType> getAllPromptPayType() {
        List<PromptPayType> categoryNames = Arrays.asList(PromptPayType.values()).stream()
                .sorted((c1, c2) -> c1.getOrder().compareTo(c2.getOrder())).collect(Collectors.toList());
        return categoryNames;
    }
}
