package com.se.shal.trading.Dao;

import com.se.shal.trading.entity.ProductOrder;
import com.se.shal.trading.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public ProductOrder save(ProductOrder productOrder) {
        return orderRepository.save(productOrder);
    }

    @Override
    public List<ProductOrder> getOrderByProductId(Long id) {
        return orderRepository.findOrderByProductsId(id);
    }
}
