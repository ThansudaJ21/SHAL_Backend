package com.se.shal.shop.service;

import com.se.shal.security.dao.AuthorityDao;
import com.se.shal.security.dao.UserDao;
import com.se.shal.security.entity.Authority;
import com.se.shal.security.entity.AuthorityName;
import com.se.shal.security.entity.User;
import com.se.shal.shop.dao.FailureReasonDao;
import com.se.shal.shop.dao.FailureReasonListDao;
import com.se.shal.shop.dao.ShopDao;
import com.se.shal.shop.dto.ShopStatusInputDto;
import com.se.shal.shop.entity.*;
import com.se.shal.shop.graphql.entity.ShopQueryFilter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopDao shopDao;
    @Autowired
    FailureReasonListDao failureReasonListDao;
    @Autowired
    FailureReasonDao failureReasonDao;
    @Autowired
    UserDao userDao;
    @Autowired
    AuthorityDao authorityDao;

    @Transactional
    @Override
    public Shop registerShop(Long userId, Shop shop) {
        User user = userDao.findById(userId);
        Shop newShop = Shop.builder()
                .shopName(shop.getShopName())
                .idCard(shop.getIdCard())
                .shopLogoImagePath(shop.getShopLogoImagePath())
                .selfiePhotoWithIdCardPath(shop.getSelfiePhotoWithIdCardPath())
                .promptPay(shop.getPromptPay())
                .email(shop.getEmail())
                .user(user)
                .shopStatus(ShopStatusName.DISABLE)
                .shopAddress(shop.getShopAddress())
                .build();
        return shopDao.save(newShop);
    }


    @Override
    public Shop getRegisterShop(Long id) {
        Shop shop = shopDao.findById(id);
        Hibernate.initialize(shop.getUser());
        return shop;
    }

    @Transactional
    @Override
    public Shop updateShopStatus(ShopStatusInputDto shopStatusInputDto, Long userId) {
        Authority seller = authorityDao.findByName(AuthorityName.SELLER);
        User user = userDao.findById(userId);
        Shop shop = shopDao.findById(shopStatusInputDto.getId());
        if (shop.getShopStatus() == ShopStatusName.ENABLE) {
            user.getAuthorities().remove(seller);
            shop.setShopStatus(ShopStatusName.DISABLE);
        } else if (shop.getShopStatus() == ShopStatusName.DISABLE) {
            shop.setShopStatus(ShopStatusName.ENABLE);
            user.getAuthorities().add(seller);
            userDao.save(user);
            shop.getFailureReasonLists().removeAll(shop.getFailureReasonLists());
        }
        return shopDao.save(shop);
    }


    @Transactional
    @Override
    public Page<Shop> findShopByFilterByShopNameOrShopStatus(ShopQueryFilter filter, PageRequest pageRequest) {
        return shopDao.getShopByFilter(filter, pageRequest);
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
                                        .build());
                            },
                            () -> {
                                throw new RuntimeException();
                            }
                    );
        }
        shop1.setFailureReasonLists(output);
        return failureReasonListDao.save(output);
    }

    @Override
    public List<FailureReason> getFailureReason() {
        return failureReasonDao.findAll();
    }

    @Transactional
    @Override
    public Shop findByUserId(Long userId) {
        return shopDao.findByUserId(userId);
    }
}
