package com.se.shal.shop.service;

import com.se.shal.shop.dao.ShopDao;
import com.se.shal.shop.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ShopServiceImpl implements ShopService{

    @Autowired
    ShopDao shopDao;

    @Transactional
    @Override
    public Shop registerShop(Shop shop) {
        Shop newShop = Shop.builder()
                .shopName(shop.getShopName())
                .idCard(shop.getIdCard())
                .shopLogoImagePath(shop.getShopLogoImagePath())
                .selfiePhotoWithIdCardPath(shop.getSelfiePhotoWithIdCardPath())
                .promptPay(shop.getPromptPay())
                .email(shop.getEmail())
                .shopAddress(shop.getShopAddress())
                .build();
        return shopDao.save(newShop);
    }


    @Override
    public Shop getRegisterShop(Long id) {
        return shopDao.findById(id);
    }
}
