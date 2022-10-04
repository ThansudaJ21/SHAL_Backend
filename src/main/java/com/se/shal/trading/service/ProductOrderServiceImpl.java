package com.se.shal.trading.service;

import com.se.shal.product.dao.OptionsDao;
import com.se.shal.product.dao.ProductDao;
import com.se.shal.product.dao.VariationDao;
import com.se.shal.product.entity.Options;
import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.Variations;
import com.se.shal.security.dao.UserDao;
import com.se.shal.security.entity.User;
import com.se.shal.shop.dao.ShopDao;
import com.se.shal.shop.entity.Shop;
import com.se.shal.trading.dao.ProductOrderDao;
import com.se.shal.trading.dao.UserAddressDao;
import com.se.shal.trading.dto.ProductOrderInputDto;
import com.se.shal.trading.entity.ProductOrder;
import com.se.shal.trading.entity.UserAddress;
import com.se.shal.trading.entity.enumeration.OrderStatus;
import com.se.shal.trading.entity.enumeration.PaymentStatus;
import com.se.shal.trading.exception.MaximumQuantityException;
import com.se.shal.trading.exception.ProductSoldOutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    UserAddressDao userAddressDao;

    @Transactional
    @Override
    public ProductOrder buyProduct(ProductOrderInputDto productOrderInputDto) {
        User user = userDao.findById(productOrderInputDto.getUsers());
        Product product = productDao.getProduct(productOrderInputDto.getProducts());
        UserAddress userAddress = userAddressDao.findById(productOrderInputDto.getUserAddress());
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
                    .options(optionsDao.findById(productOrderInputDto.getOption()))
                    .paymentStatus(PaymentStatus.UNPAID)
                    .userAddress(userAddress)
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

        Integer storage = product.getStorage();
        List<ProductOrder> productOrderList = productOrderDao.findAll();
        if (!productOrderList.isEmpty()) {
            productOrderList.forEach(productOrder -> {
                Options options = optionsDao.findById(productOrderInputDto.getOption());
                if (Objects.equals(productOrder.getQuantity(), options.getStock()) && Objects.equals(productOrder.getOptions(), options)) {
                    throw new MaximumQuantityException();
                }
            });
        }
        if (productOrderInputDto.getQuantity() <= storage && productOrderInputDto.getQuantity() > 0) {
            ProductOrder newProductOrder = ProductOrder.builder()
                    .products(product)
                    .dateTime(LocalDateTime.now())
                    .quantity(productOrderInputDto.getQuantity())
                    .totalPrice(product.getSalePrice() * productOrderInputDto.getQuantity())
                    .orderStatus(OrderStatus.ADD_TO_CART)
                    .options(optionsDao.findById(productOrderInputDto.getOption()))
                    .paymentStatus(PaymentStatus.UNPAID)
                    .users(user)
                    .shop(shop)
                    .build();
            return productOrderDao.save(newProductOrder);
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
