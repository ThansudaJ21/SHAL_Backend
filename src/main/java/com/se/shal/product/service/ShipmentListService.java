package com.se.shal.product.service;

import com.se.shal.product.dto.input.InputShipmentList;
import com.se.shal.product.dto.input.InputUpdateShipmentList;
import com.se.shal.product.dto.input.UpdateShipmentList;
import com.se.shal.product.entity.ShipmentList;

public interface ShipmentListService {
    ShipmentList save(Long productId, InputShipmentList shipmentList);
    ShipmentList getShipmentLists(Long productId, Long id);
    ShipmentList updateShipmentLists( UpdateShipmentList shipmentList);
}
