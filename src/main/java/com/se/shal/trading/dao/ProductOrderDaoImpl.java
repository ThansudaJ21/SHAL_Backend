package com.se.shal.trading.dao;

import com.se.shal.trading.entity.ProductOrder;
import com.se.shal.trading.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductOrderDaoImpl implements ProductOrderDao {

    @Autowired
    ProductOrderRepository productOrderRepository;

    @Override
    public com.se.shal.trading.entity.ProductOrder save(com.se.shal.trading.entity.ProductOrder productOrder) {
        return this.productOrderRepository.save(productOrder);
    }

    @Override
    public List<ProductOrder> findByUsersIdOrProductsIdOrShopId(Long userId, Long productId, Long shopId) {
        return productOrderRepository.findByUsersIdOrProductsIdOrShopId(userId, productId, shopId);
    }

    @Override
    public List<ProductOrder> findByUsersId(Long userId) {
        return productOrderRepository.findByUsersId(userId);
    }

    @Override
    public List<ProductOrder> findAll() {
        return productOrderRepository.findAll();
    }

    @Override
    public ProductOrder findById(Long id) {
        return productOrderRepository.findById(id).orElse(null);
    }
}
