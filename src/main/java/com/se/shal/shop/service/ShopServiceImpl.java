package com.se.shal.shop.service;

import com.se.shal.shop.dao.ShopDao;
import com.se.shal.shop.entity.Shop;
import com.se.shal.shop.entity.ShopStatus;
import com.se.shal.shop.entity.ShopStatusName;
import com.se.shal.shop.graphql.entity.ShopQueryFilterByShopName;
import com.se.shal.shop.graphql.entity.ShopQueryFilterByShopStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
                .shopStatus(ShopStatusName.DISABLE)
                .shopAddress(shop.getShopAddress())
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
        if (shop1.getShopStatus() == ShopStatusName.ENABLE) {
            shop1.setShopStatus(ShopStatusName.DISABLE);
        } else if (shop1.getShopStatus() == ShopStatusName.DISABLE){
            shop1.setShopStatus(ShopStatusName.ENABLE);
        }
        return shopDao.save(shop1);
    }

    @Override
    public List<Shop> getAllShop() {
        return shopDao.getAllShop();
    }

    @Transactional
    @Override
    public Page<Shop> findShopByFilterByShopName(ShopQueryFilterByShopName filter, PageRequest pageRequest) {
        return shopDao.getShopByFilterByShopName(filter, pageRequest);
    }


    @Transactional
    @Override
    public Page<Shop> findShopByFilterByShopStatus(ShopQueryFilterByShopStatus filter, PageRequest pageRequest) {
        if (filter.getShopStatus().equalsIgnoreCase(ShopStatusName.DISABLE.toString())) {
        } else if (filter.getShopStatus().equalsIgnoreCase(ShopStatusName.ENABLE.toString())) {
            return shopDao.getShopFilterByShopStatus(filter, pageRequest);
        }
        return shopDao.getShopFilterByShopStatus(filter, pageRequest);
    }
}
