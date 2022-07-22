package com.se.shal.trading.service;

import com.se.shal.product.dao.ProductDao;
import com.se.shal.product.entity.Product;
import com.se.shal.security.dao.UserDao;
import com.se.shal.security.entity.User;
import com.se.shal.shop.dao.ShopDao;
import com.se.shal.trading.Dao.TradingHistoryDao;
import com.se.shal.trading.entity.TradingHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class TradingServiceImpl implements TradingService {

    @Autowired
    ProductDao productDao;
    @Autowired
    ShopDao shopDao;
    @Autowired
    UserDao userDao;

    @Autowired
    TradingHistoryDao tradingHistoryDao;

    @Transactional
    @Override
    public Product buyProduct(Long userId, Long productId, Integer quantity) {
        User user = userDao.findById(userId);
        Product product = productDao.getProduct(productId);
        Integer storage = product.getStorage();
        if (quantity <= storage && quantity > 0) {
            int finalStorage = 0;
            finalStorage = storage - quantity;
            product.setStorage(finalStorage);
            TradingHistory tradingHistory = TradingHistory.builder()
                    .products(List.of(product))
                    .dateTime(LocalDateTime.now())
                    .users(List.of(user))
                    .build();
            tradingHistoryDao.save(tradingHistory);
        }
        return productDao.saveProduct(product);
    }
}
