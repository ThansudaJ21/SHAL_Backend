package com.se.shal.product.service;

import com.se.shal.product.dto.InputShipmentList;
import com.se.shal.product.entity.ShipmentList;
import com.se.shal.product.entity.Variations;

import java.util.List;

public interface ShipmentListService {
    ShipmentList save(Long productId, InputShipmentList shipmentList);
}
