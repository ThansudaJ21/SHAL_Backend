package com.se.shal.trading.Dao;

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
    public List<ProductOrder> findProductOrderByProductsId(Long productId) {
        return productOrderRepository.findProductOrderByProductsId(productId);
    }

    @Override
    public List<ProductOrder> findProductOrderByShopId(Long shopId) {
        return productOrderRepository.findProductOrderByShopId(shopId);
    }
}
