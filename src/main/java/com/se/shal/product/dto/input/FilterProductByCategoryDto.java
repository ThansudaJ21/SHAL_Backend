package com.se.shal.product.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterProductByCategoryDto {
    Long id;
    String productName;
    String details;
    List<String> imagesPath;
    String category;
    Long shopId;
}
