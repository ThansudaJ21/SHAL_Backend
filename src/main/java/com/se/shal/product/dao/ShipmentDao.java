package com.se.shal.product.dao;

import com.se.shal.product.entity.Shipment;

public interface ShipmentDao {
    Shipment findShipmentByName(String name);
}
