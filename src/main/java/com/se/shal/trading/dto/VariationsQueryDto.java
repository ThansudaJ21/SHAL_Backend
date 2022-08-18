package com.se.shal.trading.dto;

import com.se.shal.product.dto.OptionsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariationsQueryDto {
    Long id;
    String variationName;

}
