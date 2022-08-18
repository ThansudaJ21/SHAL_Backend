package com.se.shal.shop.service;

import com.se.shal.shop.entity.Shop;
import com.se.shal.shop.entity.ShopAddress;
import com.se.shal.shop.graphql.entity.ShopQueryFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;

//import static org.junit.jupiter.api.AssertEquals.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ShopServiceTest {
    @Autowired
    ShopService shopService;

    ShopAddress shopAddress = ShopAddress.builder()
            .province("Chiang mai")
            .postalCode("50200")
            .subDistrict("Sutep")
            .moo("5")
            .houseNumber("169/1")
            .district("Meaung Chiang mai")
            .build();


    @Test
    @Transactional
    void getRegisterShop() {
        int i = 9;
        Long l = (long) i;
        Shop shop = shopService.getRegisterShop(l);
        System.out.println(shop);
    }



    @Test
    @Transactional
    void shopQueryFilterByShopNameOrShopStatus() {
        ShopQueryFilter shopQueryFilter = ShopQueryFilter.builder()
                .shopName("")
                .shopStatus("enable")
                .build();
        PageRequest paginacao = PageRequest.of(0, 3);
        Page<Shop> shop = shopService.findShopByFilterByShopNameOrShopStatus(shopQueryFilter,paginacao);
        assertNull(shop);
    }

    @Test
    @Transactional
    void shopQueryFilterByShopNameOrShopStatus2() {
        ShopQueryFilter shopQueryFilter = ShopQueryFilter.builder()
                .shopName("da")
                .shopStatus(null)
                .build();
        PageRequest paginacao = PageRequest.of(0, 3);
        Page<Shop> shop = shopService.findShopByFilterByShopNameOrShopStatus(shopQueryFilter,paginacao);
        assertEquals(3,shop.getTotalElements());
        assertEquals(1,shop.getContent().get(0).getId());
        assertEquals(1,shop.getContent().get(0).getId());
    }
}
