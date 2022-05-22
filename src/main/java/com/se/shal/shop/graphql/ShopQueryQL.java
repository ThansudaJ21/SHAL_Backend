package com.se.shal.shop.graphql;

import com.se.shal.shop.entity.Shop;
import com.se.shal.shop.service.ShopService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ShopQueryQL implements GraphQLQueryResolver {

    @Autowired
    ShopService shopService;
    @Transactional
    Shop getRegisterShop(Long id){
        Shop shop = shopService.getRegisterShop(id);
        return shop;
    }
}
