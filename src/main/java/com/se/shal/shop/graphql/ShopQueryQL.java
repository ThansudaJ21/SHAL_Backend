package com.se.shal.shop.graphql;

import com.se.shal.shop.entity.Shop;
import com.se.shal.shop.graphql.entity.ShopQueryFilterByShopName;
import com.se.shal.shop.graphql.entity.ShopQueryFilterByShopStatus;
import com.se.shal.shop.service.ShopService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;


import javax.transaction.Transactional;
import java.util.List;

@Component
public class ShopQueryQL implements GraphQLQueryResolver {

    @Autowired
    ShopService shopService;


    @Transactional

    Shop getRegisterShop(Long id){
        Shop shop = shopService.getRegisterShop(id);
        return shop;
    }

    @Transactional
    List<Shop> getAllShop() {
        List<Shop> shops = shopService.getAllShop();
        return shops;
    }

    @Transactional
    Page<Shop> shopByFilterByShopNameOrShopStatus(ShopQueryFilterByShopName filter, int pageNo, int pageSize){
        return shopService.findShopByFilterByShopNameOrShopStatus(filter, PageRequest.of(pageNo,pageSize));
    }

    @Transactional
    List<Shop> shopFilterByStatus(String  status){
        return shopService.shopFilterByStatus(status);
    }
}
