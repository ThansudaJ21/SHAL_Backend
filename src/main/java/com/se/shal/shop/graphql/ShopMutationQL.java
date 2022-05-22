package com.se.shal.shop.graphql;

import com.se.shal.shop.dto.ShopDto;
import com.se.shal.shop.entity.Shop;
import com.se.shal.shop.service.ShopService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ShopMutationQL implements GraphQLMutationResolver {
    @Autowired
    ShopService shopService;

    @Transactional
    ShopDto registerShop(Shop shop){
        Shop newShop = shopService.registerShop(shop);
        return ShalMapper.INSTANCE.registerShop(newShop);
    }
}
