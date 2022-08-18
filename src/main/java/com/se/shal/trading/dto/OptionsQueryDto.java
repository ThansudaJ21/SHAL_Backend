package com.se.shal.trading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionsQueryDto {
    Long id;
    String optionName;
    Integer price;
    Integer stock;
    String image;
}
