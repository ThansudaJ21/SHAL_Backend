package com.se.shal.product.dto;

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
    String category;
    Long shopId;
}
