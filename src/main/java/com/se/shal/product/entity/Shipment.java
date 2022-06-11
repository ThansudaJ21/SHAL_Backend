package com.se.shal.product.entity;

import com.se.shal.product.entity.enumeration.ShipmentName;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @Enumerated(EnumType.STRING)
    private ShipmentName shipmentName;


    public Shipment() {

    }
}
