package com.se.shal.trading.service;

import com.se.shal.product.dao.OptionsDao;
import com.se.shal.product.dao.ProductDao;
import com.se.shal.product.dao.VariationDao;
import com.se.shal.product.entity.Product;
import com.se.shal.security.dao.UserDao;
import com.se.shal.security.entity.User;
import com.se.shal.shop.dao.ShopDao;
import com.se.shal.shop.entity.Shop;
import com.se.shal.trading.dao.ProductOrderDao;
import com.se.shal.trading.dto.ProductOrderInputDto;
import com.se.shal.trading.entity.ProductOrder;
import com.se.shal.trading.entity.enumeration.OrderStatus;
import com.se.shal.trading.entity.enumeration.PaymentStatus;
import com.se.shal.trading.exception.ProductSoldOutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    ProductDao productDao;
    @Autowired
    ShopDao shopDao;
    @Autowired
    UserDao userDao;
    @Autowired
    VariationDao variationDao;
    @Autowired
    ProductOrderDao productOrderDao;
    @Autowired
    OptionsDao optionsDao;


    @Transactional
    @Override
    public ProductOrder buyProduct(ProductOrderInputDto productOrderInputDto) {
        User user = userDao.findById(productOrderInputDto.getUsers());
        Product product = productDao.getProduct(productOrderInputDto.getProducts());
        List<Long> optionsList = productOrderInputDto.getOptionsList();
        Shop shop = shopDao.findById(productOrderInputDto.getShop());
        Integer storage = product.getStorage();
        if (productOrderInputDto.getQuantity() <= storage && productOrderInputDto.getQuantity() > 0) {
            int finalStorage = 0;
            finalStorage = storage - productOrderInputDto.getQuantity();
            product.setStorage(finalStorage);
            ProductOrder productOrder = ProductOrder.builder()
                    .products(product)
                    .dateTime(LocalDateTime.now())
                    .quantity(productOrderInputDto.getQuantity())
                    .totalPrice(product.getSalePrice() * productOrderInputDto.getQuantity())
                    .orderStatus(OrderStatus.BUY)
                    .optionsList(optionsDao.findByIds(optionsList))
                    .paymentStatus(PaymentStatus.UNPAID)
                    .shop(shop)
                    .users(user)
                    .build();
            return productOrderDao.save(productOrder);
        } else {
            throw new ProductSoldOutException();
        }
    }

    @Transactional
    @Override
    public ProductOrder addToCart(ProductOrderInputDto productOrderInputDto) {
        User user = userDao.findById(productOrderInputDto.getUsers());
        Product product = productDao.getProduct(productOrderInputDto.getProducts());
        Shop shop = shopDao.findById(productOrderInputDto.getShop());
        List<Long> optionsList = productOrderInputDto.getOptionsList();
        Integer storage = product.getStorage();
        if (productOrderInputDto.getQuantity() <= storage && productOrderInputDto.getQuantity() > 0) {
            ProductOrder productOrder = ProductOrder.builder()
                    .products(product)
                    .dateTime(LocalDateTime.now())
                    .quantity(productOrderInputDto.getQuantity())
                    .totalPrice(product.getSalePrice() * productOrderInputDto.getQuantity())
                    .orderStatus(OrderStatus.ADD_TO_CART)
                    .optionsList(optionsDao.findByIds(optionsList))
                    .paymentStatus(PaymentStatus.UNPAID)
                    .users(user)
                    .shop(shop)
                    .build();
            return productOrderDao.save(productOrder);
        } else {
            throw new ProductSoldOutException();
        }
    }

    @Transactional
    @Override
    public List<ProductOrder> findByUsersIdOrProductsIdOrShopId(Long userId, Long productId, Long shopId) {
        return productOrderDao.findByUsersIdOrProductsIdOrShopId(userId, productId, shopId);
    }

    @Transactional
    @Override
    public List<ProductOrder> getAddToCartProduct(Long userId) {
        List<ProductOrder> productOrderList = productOrderDao.findByUsersId(userId);
        List<ProductOrder> addToCartList = new ArrayList<>();
        for (ProductOrder productOrder : productOrderList) {
            if (productOrder.getOrderStatus().equals(OrderStatus.ADD_TO_CART)) {
                addToCartList.add(productOrder);
            }
        }
        return addToCartList;
    }
}
