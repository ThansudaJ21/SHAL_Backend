package com.se.shal.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopStatusInputDto {
    Long id;
    Long userId;
    String shopStatus;
}
