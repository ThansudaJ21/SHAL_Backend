package com.se.shal.product.dao;

import com.se.shal.product.entity.ShipmentList;

public interface ShipmentListDao {
    ShipmentList save(ShipmentList shipmentList);

    ShipmentList findById(Long id);

    ShipmentList getShipmentList(Long id);
}
