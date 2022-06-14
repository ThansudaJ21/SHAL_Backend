package com.se.shal.shop.dao;

import com.se.shal.shop.entity.ShopStatus;
import com.se.shal.shop.repository.ShopStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShopStatusDaoImpl implements ShopStatusDao{
    @Autowired
    ShopStatusRepository shopStatusRepository;

    @Override
    public ShopStatus save(ShopStatus shopStatus) {
        return shopStatusRepository.save(shopStatus);
    }
}
