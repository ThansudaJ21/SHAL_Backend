package com.se.shal.product.dao;

import com.se.shal.product.entity.Shipment;
import com.se.shal.product.entity.ShipmentName;
import com.se.shal.product.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShipmentDaoDbImpl implements ShipmentDao{

    @Autowired
    ShipmentRepository shipmentRepository;
    @Override
    public Shipment findShipmentByName(String name) {
        ShipmentName dsg = ShipmentName.valueOf(name);
        Shipment output = shipmentRepository.findByShipmentName(dsg);
        return output;
    }
}
