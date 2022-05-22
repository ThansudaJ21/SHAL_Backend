package com.se.shal.shop.service;

import com.se.shal.shop.dao.ShopDao;
import com.se.shal.shop.entity.Shop;
import com.se.shal.shop.entity.ShopStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService{

    @Autowired
    ShopDao shopDao;

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
                .shopStatus(ShopStatus.Disable)
                .build();
        return shopDao.save(newShop);
    }

    @Override
    public Shop getRegisterShop(Long id) {
        return shopDao.findById(id);
    }

    @Override
    public Shop updateShopStatus(Shop shop) {
        Shop shop1 = shopDao.findById(shop.getId());
        if (shop1.getShopStatus() == ShopStatus.Enable) {
            shop1.setShopStatus(ShopStatus.Disable);
        } else if (shop1.getShopStatus() == ShopStatus.Disable){
            shop1.setShopStatus(ShopStatus.Enable);
        }
        return shopDao.save(shop1);
    }

    @Override
    public List<Shop> getAllShop() {
        return shopDao.getAllShop();
    }
}
