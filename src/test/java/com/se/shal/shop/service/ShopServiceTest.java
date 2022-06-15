package com.se.shal.shop.service;

import com.se.shal.shop.entity.Shop;
import com.se.shal.shop.entity.ShopAddress;
import com.se.shal.shop.entity.ShopStatusName;
import com.se.shal.shop.graphql.entity.ShopQueryFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
    void saveShop() {
        Shop shop = shopService.registerShop(Shop.builder()
                .shopAddress(shopAddress)
                .selfiePhotoWithIdCardPath("https://storage.googleapis.com/download/storage/v1/b/imageupload-2fa82.appspot.com/o/2565-06-14%20051621924-keyboard.jpg?generation=1655158581510938&alt=media")
                .shopLogoImagePath("https://storage.googleapis.com/download/storage/v1/b/imageupload-2fa82.appspot.com/o/2565-06-14%20051620416-skirt.jpg?generation=1655158581514129&alt=media")
                .idCard("1570701286356")
                .promptPay("0953585658")
                .shopName("mamamoo giftshop")
                .email("mamamoo1@gmail.com")
                .build());
        System.out.println(shop);
    }

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
    void updateShopStatus() {
        int i = 1;
        Long l = (long) i;
        Shop shop = shopService.updateShopStatus(Shop.builder()
                .id(l)
                .shopStatus(ShopStatusName.DISABLE)
                .build());
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
