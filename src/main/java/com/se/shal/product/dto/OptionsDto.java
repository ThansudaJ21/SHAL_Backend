package com.se.shal.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionsDto {
    Long id;
    String optionName;
    Integer price;
    Integer stock;
    String image;
}
