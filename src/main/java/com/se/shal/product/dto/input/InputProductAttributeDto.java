package com.se.shal.product.dto.input;

import com.se.shal.product.dto.AttributeDto;
import com.se.shal.product.entity.Attribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputProductAttributeDto {
    String text;
    AttributeDto attribute;
    Long productId;
}
