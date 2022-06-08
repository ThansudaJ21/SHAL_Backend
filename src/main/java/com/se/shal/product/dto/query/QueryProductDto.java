package com.se.shal.product.dto.query;

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
public class QueryProductDto {
    Long id;
    String productName;
    String details;
    String productStatus;
    List<String> imagesPath;
    String category;
    Long shopId;
}
