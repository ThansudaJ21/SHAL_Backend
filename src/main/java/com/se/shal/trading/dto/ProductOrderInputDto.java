package com.se.shal.trading.dto;

import com.se.shal.product.entity.Shipment;
import com.se.shal.trading.entity.UserAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderInputDto {
    Long id;
    LocalDateTime dateTime;
    Double totalPrice;
    Integer quantity;
    String paymentStatus;
    String orderStatus;
    String trackingNumber;
    Long userAddress;
    String slipPaymentUrl;
    String shipment;
    Long products;
    Long shop;
    Long users;
    Long option;
}
