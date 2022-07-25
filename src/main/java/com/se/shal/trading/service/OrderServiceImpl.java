package com.se.shal.trading.service;

import com.se.shal.product.dao.OptionsDao;
import com.se.shal.product.dao.ProductDao;
import com.se.shal.product.dao.VariationDao;
import com.se.shal.product.entity.Product;
import com.se.shal.security.dao.UserDao;
import com.se.shal.security.entity.User;
import com.se.shal.shop.dao.ShopDao;
import com.se.shal.trading.Dao.OrderDao;
import com.se.shal.trading.dto.OrderInputDto;
import com.se.shal.trading.entity.ProductOrder;
import com.se.shal.trading.entity.enumeration.OrderStatus;
import com.se.shal.trading.entity.enumeration.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductDao productDao;
    @Autowired
    ShopDao shopDao;
    @Autowired
    UserDao userDao;
    @Autowired
    VariationDao variationDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    OptionsDao optionsDao;

    @Transactional
    @Override
    public ProductOrder buyProduct(OrderInputDto orderInputDto) {
        User user = userDao.findById(orderInputDto.getUsers());
        Product product = productDao.getProduct(orderInputDto.getProducts());
        List<Long> variationsList = orderInputDto.getVariationsList();
        List<Long> optionsList = orderInputDto.getOptionsList();
        Integer storage = product.getStorage();
        if (orderInputDto.getQuantity() <= storage && orderInputDto.getQuantity() > 0) {
            int finalStorage = 0;
            finalStorage = storage - orderInputDto.getQuantity();
            product.setStorage(finalStorage);
            ProductOrder productOrder = ProductOrder.builder()
                    .products(product)
                    .dateTime(LocalDateTime.now())
                    .Quantity(orderInputDto.getQuantity())
                    .totalPrice(product.getSalePrice() * orderInputDto.getQuantity())
                    .orderStatus(OrderStatus.BUY)
                    .variationsList(variationDao.findByIds(variationsList))
                    .optionsList(optionsDao.findByIds(optionsList))
                    .paymentStatus(PaymentStatus.UNPAID)
                    .users(user)
                    .build();
            return orderDao.save(productOrder);
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public List<ProductOrder> getOrderByProductId(Long productId) {
        return orderDao.getOrderByProductId(productId);
    }
}
