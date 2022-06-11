package com.se.shal.product.repository;

import com.se.shal.product.entity.Shipment;
import com.se.shal.product.entity.enumeration.ShipmentName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment , Long> {
    Shipment findByShipmentName(ShipmentName shipment);
}
