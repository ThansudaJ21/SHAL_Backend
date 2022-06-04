package com.se.shal.product.service;

import com.se.shal.product.dao.*;

import com.se.shal.product.dto.VariationsDto;
import com.se.shal.product.entity.*;
import com.se.shal.shop.dao.ShopDao;
import com.se.shal.shop.entity.Shop;
import com.se.shal.util.ShalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;
    @Autowired
    ShopDao shopDao;

    @Transactional
    @Override
    public Product saveProduct(Long shopId, Product product) {
        Shop shop = shopDao.findById(shopId);
        product.setShop(shop);
        return productDao.saveProduct(product);
    }

    @Transactional
    @Override
    public Product getProduct(Long id) {
        return productDao.getProduct(id);
    }
}
