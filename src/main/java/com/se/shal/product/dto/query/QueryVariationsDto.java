package com.se.shal.product.dto.query;

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
public class QueryVariationsDto {
    Long id;
    String variationName;
    List<OptionsDto> options;
    Long productId;
}
