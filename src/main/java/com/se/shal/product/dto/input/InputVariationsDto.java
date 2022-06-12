package com.se.shal.product.dto.input;

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
public class InputVariationsDto {
    Long id;
    String variationName;
    List<OptionsDto> options;

}
