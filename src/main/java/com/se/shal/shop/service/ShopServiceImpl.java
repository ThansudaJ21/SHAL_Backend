package com.se.shal.shop.service;

import com.se.shal.product.entity.ProductAttribute;
import com.se.shal.product.entity.Variations;
import com.se.shal.shop.dao.FailureReasonDao;
import com.se.shal.shop.dao.FailureReasonListDao;
import com.se.shal.shop.dao.ShopDao;
import com.se.shal.shop.entity.FailureReason;
import com.se.shal.shop.entity.FailureReasonList;
import com.se.shal.shop.entity.Shop;
import com.se.shal.shop.entity.ShopStatusName;
import com.se.shal.shop.graphql.entity.ShopQueryFilterByShopName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopDao shopDao;

    @Autowired
    FailureReasonListDao failureReasonListDao;
    @Autowired
    FailureReasonDao failureReasonDao;

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
        } else if (shop1.getShopStatus() == ShopStatusName.DISABLE) {
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
    public Page<Shop> findShopByFilterByShopNameOrShopStatus(ShopQueryFilterByShopName filter, PageRequest pageRequest) {
        return shopDao.getShopByFilterByShopName(filter, pageRequest);
    }

    @Transactional
    @Override
    public List<Shop> shopFilterByStatus(String status) {
        List<Shop> shops = shopDao.getAllShop();
        List<Shop> output = new ArrayList<>();
        for (Shop shop : shops) {
            if (Objects.equals(status, shop.getShopStatus().getShopStatus())) {
                output.add(shop);
            }
        }
        return output;
    }

    @Transactional
    @Override
    public List<FailureReasonList> saveFailureReason(Long shopId, List<FailureReasonList> failureReason) {
        Shop shop1 = shopDao.findById(shopId);
        List<FailureReasonList> output = new ArrayList<>();
        for (FailureReasonList failureReasonList : failureReason) {
            failureReasonDao.findByReason(failureReasonList.getFailureReasons().getReason())
                    .ifPresentOrElse(
                            (reason) -> {
                                output.add(FailureReasonList.builder()
                                        .failureReasons(reason)
                                        .text(failureReasonList.getText())
                                        .shop(shop1)
                                        .build());
                            },
                            () -> {
                                throw new RuntimeException();
                            }
                    );
        }
        return failureReasonListDao.save(output);
    }
}
