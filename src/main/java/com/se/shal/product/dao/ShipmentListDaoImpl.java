package com.se.shal.product.dao;

import com.se.shal.product.entity.ShipmentList;
import com.se.shal.product.repository.ShipmentListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShipmentListDaoImpl implements ShipmentListDao {
    @Autowired
    ShipmentListRepository shipmentListRepository;

    @Override
    public ShipmentList save(ShipmentList shipmentList) {
        return shipmentListRepository.save(shipmentList);
    }

    @Override
    public ShipmentList findById(Long id) {
        return shipmentListRepository.findById(id).orElse(null);


    }

    @Override
    public ShipmentList getShipmentList(Long id) {
        return shipmentListRepository.findById(id).orElse(null);
    }
}
