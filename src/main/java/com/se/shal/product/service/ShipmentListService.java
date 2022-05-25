package com.se.shal.product.service;

import com.se.shal.product.dto.InputShipmentList;
import com.se.shal.product.entity.ShipmentList;

public interface ShipmentListService {
    ShipmentList save(Long productId, InputShipmentList shipmentList);
}
