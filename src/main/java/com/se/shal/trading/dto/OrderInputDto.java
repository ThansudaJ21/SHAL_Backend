package com.se.shal.trading.dto;

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
public class OrderInputDto {
    Long id;
    LocalDateTime dateTime;
    Double totalPrice;
    Integer quantity;
    String paymentStatus;
    String orderStatus;

    Long products;
    Long users;
    List<Long> variationsList;
    List<Long> optionsList;
}
