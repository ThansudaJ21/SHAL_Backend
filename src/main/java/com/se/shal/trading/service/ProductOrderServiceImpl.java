package com.se.shal.trading.service;

import com.se.shal.product.dao.OptionsDao;
import com.se.shal.product.dao.ProductDao;
import com.se.shal.product.dao.ShipmentDao;
import com.se.shal.product.entity.Options;
import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.Shipment;
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
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderService {
    final ProductDao productDao;
    final ShopDao shopDao;
    final UserDao userDao;
    final ProductOrderDao productOrderDao;
    final OptionsDao optionsDao;
    final UserAddressDao userAddressDao;

    final ShipmentDao shipmentDao;

    @Transactional
    @Override
    public ProductOrder buyProduct(ProductOrderInputDto productOrderInputDto) {
        User user = userDao.findById(productOrderInputDto.getUsers());
        Product product = productDao.getProduct(productOrderInputDto.getProducts());
        UserAddress userAddress = userAddressDao.findById(productOrderInputDto.getUserAddress());
        Shop shop = shopDao.findById(productOrderInputDto.getShop());
        Integer storage = product.getStorage();
        Shipment shipment = shipmentDao.findShipmentByName(productOrderInputDto.getShipment());
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
                    .shipment(shipment)
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
        List<ProductOrder> p = productOrderDao.findByUsersIdOrProductsIdOrShopId(userId, productId, shopId);
        List<ProductOrder> newOrder = new ArrayList<>();
        p.forEach(productOrder -> {
            if (!productOrder.getOrderStatus().equals(OrderStatus.DELETE) && !productOrder.getOrderStatus().equals(OrderStatus.CANCEL)) {
                Hibernate.initialize(productOrder.getShipment());
                newOrder.add(productOrder);
            }
        });
        return newOrder;
    }

    @Transactional
    @Override
    public List<ProductOrder> getAddToCartProduct(Long userId) {
        List<ProductOrder> productOrderList = productOrderDao.findByUsersId(userId);
        List<ProductOrder> addToCartList = new ArrayList<>();
        for (ProductOrder productOrder : productOrderList) {
            if (productOrder.getOrderStatus().equals(OrderStatus.ADD_TO_CART) && !productOrder.getOrderStatus().equals(OrderStatus.DELETE) && !productOrder.getOrderStatus().equals(OrderStatus.CANCEL)) {
                addToCartList.add(productOrder);
            }
        }
        return addToCartList;
    }

    @Transactional
    @Override
    public List<ProductOrder> findByShopIdAndPaymentStatus(Long shopId, String paymentStatus) {
        List<ProductOrder> newOrder = new ArrayList<>();
        if (paymentStatus.equals(PaymentStatus.UNPAID.name())) {
            List<ProductOrder> p = productOrderDao.findByShopIdAndPaymentStatus(shopId, PaymentStatus.UNPAID);
            p.forEach(productOrder -> {
                if (!productOrder.getOrderStatus().equals(OrderStatus.DELETE) && !productOrder.getOrderStatus().equals(OrderStatus.CANCEL)) {
                    newOrder.add(productOrder);
                }
            });
            return newOrder;
        } else if (paymentStatus.equals(PaymentStatus.PAID.name())) {
            List<ProductOrder> p = productOrderDao.findByShopIdAndPaymentStatus(shopId, PaymentStatus.PAID);
            p.forEach(productOrder -> {
                if (!productOrder.getOrderStatus().equals(OrderStatus.DELETE) && !productOrder.getOrderStatus().equals(OrderStatus.CANCEL)) {
                    newOrder.add(productOrder);
                }
            });
            return newOrder;
        } else if (paymentStatus.equals(PaymentStatus.PENDING_TO_CONFIRM.name())) {
            List<ProductOrder> p = productOrderDao.findByShopIdAndPaymentStatus(shopId, PaymentStatus.PENDING_TO_CONFIRM);
            p.forEach(productOrder -> {
                if (!productOrder.getOrderStatus().equals(OrderStatus.DELETE) && !productOrder.getOrderStatus().equals(OrderStatus.CANCEL)) {
                    newOrder.add(productOrder);
                }
            });
            return newOrder;
        } else {
            List<ProductOrder> p = productOrderDao.findByShopIdAndPaymentStatus(shopId, PaymentStatus.DELIVERED);
            p.forEach(productOrder -> {
                if (!productOrder.getOrderStatus().equals(OrderStatus.DELETE) && !productOrder.getOrderStatus().equals(OrderStatus.CANCEL)) {
                    newOrder.add(productOrder);
                }
            });
            return newOrder;
        }
    }

    @Transactional
    @Override
    public ProductOrder updatePaymentStatusToPaid(Long productOrderId) {
        ProductOrder p = productOrderDao.findById(productOrderId);
        if (p.getPaymentStatus().equals(PaymentStatus.PENDING_TO_CONFIRM) && !p.getOrderStatus().equals(OrderStatus.DELETE) && !p.getOrderStatus().equals(OrderStatus.CANCEL)) {
            p.setPaymentStatus(PaymentStatus.PAID);
        }
        return productOrderDao.save(p);
    }

    @Transactional
    @Override
    public ProductOrder updatePaymentStatusToDelivered(Long productOrderId, String trackingNumber) {
        ProductOrder p = productOrderDao.findById(productOrderId);
        if (p.getPaymentStatus().equals(PaymentStatus.PAID) && !p.getOrderStatus().equals(OrderStatus.DELETE) && !p.getOrderStatus().equals(OrderStatus.CANCEL)) {
            p.setTrackingNumber(trackingNumber);
            p.setPaymentStatus(PaymentStatus.DELIVERED);
        }
        return productOrderDao.save(p);
    }

    @Override
    public ProductOrder updatePaymentStatusToPendingToConfirm(Long productOrderId, String slipPaymentUrl) {
        ProductOrder p = productOrderDao.findById(productOrderId);
        if (p.getPaymentStatus().equals(PaymentStatus.UNPAID) && !p.getOrderStatus().equals(OrderStatus.DELETE) && !p.getOrderStatus().equals(OrderStatus.CANCEL)) {
            p.setSlipPaymentUrl(slipPaymentUrl);
            p.setPaymentStatus(PaymentStatus.PENDING_TO_CONFIRM);
        }
        return productOrderDao.save(p);
    }

    @Transactional
    @Override
    public ProductOrder getProductOrderById(Long productOrderId) {
        ProductOrder p = productOrderDao.findById(productOrderId);
        if (!p.getOrderStatus().equals(OrderStatus.DELETE) && !p.getOrderStatus().equals(OrderStatus.CANCEL)) {
            Hibernate.initialize(p.getProducts());
            Hibernate.initialize(p.getShop());
            Hibernate.initialize(p.getUsers());
            Hibernate.initialize(p.getUserAddress());
            Hibernate.initialize(p.getOptions());
            return p;
        }
        return null;
    }

    @Transactional
    @Override
    public ProductOrder deleteProductOrderById(Long productOrderId) {
        ProductOrder p = productOrderDao.findById(productOrderId);
        if (!p.getOrderStatus().equals(OrderStatus.DELETE)) {
            p.setOrderStatus(OrderStatus.DELETE);
        }
        return productOrderDao.save(p);
    }

    @Transactional
    @Override
    public ProductOrder cancelProductOrderById(Long productOrderId) {
        ProductOrder p = productOrderDao.findById(productOrderId);
        if (!p.getOrderStatus().equals(OrderStatus.CANCEL)) {
            p.setOrderStatus(OrderStatus.CANCEL);
        }
        return productOrderDao.save(p);
    }
}
