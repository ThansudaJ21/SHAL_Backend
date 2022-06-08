package com.se.shal.product.dto.input;

import com.se.shal.product.entity.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputProductDto {
    Long id;
    String productName;
    String details;
    List<String> imagesPath;
    ProductStatus productStatus;
    String category;
    Long shopId;
}
