package com.se.shal.product.dto.query;

import com.se.shal.product.dto.AttributeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryProductAttributeDto {
    Long id;
    String text;
    AttributeDto attribute;
    Long productId;
}
