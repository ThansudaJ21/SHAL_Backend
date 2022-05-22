package com.se.shal.shop.dao;

import com.se.shal.shop.entity.Shop;
import com.se.shal.shop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShopDaoImpl implements ShopDao{
    @Autowired
    ShopRepository shopRepository;

    @Override
    public Shop save(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public Shop getRegisterShop(Long id) {
        return shopRepository.getById(id);
    }
}
