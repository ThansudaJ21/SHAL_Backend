package com.se.shal.product.dto;

import com.se.shal.product.entity.Options;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariationsDto {
    Long id;
    String variationName;
    List<OptionsDto> options;
    Long productId;
}
